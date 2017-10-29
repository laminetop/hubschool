package sn.projet.hubschool.controller;


import com.google.gson.Gson;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Controller Admin
 */
@Api(value = "Administration", description = "Services d'administration et supervision pour analyser au "
        + "runtime la configuration serveur.", position = 1)
@Controller
@RequestMapping(value = "/api/admin", produces = {"text/plain;charset=UTF-8", "application/json;charset=UTF-8",
        "application/hal+json;charset=UTF-8"})
public class MonitoringController {

    /**
     * Sanity Check sur la JVM. Permet d'afficher les properties du system.
     */
    @ApiOperation(value = "Retourne System.getProperties()")
    @RequestMapping(value = "/properties/system", method = RequestMethod.GET)
    @SuppressWarnings({"unchecked", "rawtypes"})
    public ResponseEntity configs() {
        Gson gson = new Gson();
        return new ResponseEntity(gson.toJson(System.getProperties()), HttpStatus.OK);
    }

    /**
     * Permet d'observer le paramétrage proxy appliqué à une URL donnée (intègre donc le "ignore
     * hosts" du paramétrage proxy).
     *
     * @param url url à tester
     * @return le proxy appliqué.
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "Retourne le paramétrage Proxy pour une URL.", notes = "Permet de tester le "
            + "paramétrage ou la présence d'un paramétrage Proxy sur le serveur.")
    @RequestMapping(value = "/proxy", method = RequestMethod.GET)
    public ResponseEntity proxy(@RequestParam(defaultValue = "http://www.google.fr")
                                        String url) {
        StringBuilder sb = new StringBuilder("");

        sb.append("detecting proxies\n");
        List<Proxy> l = null;
        try {
            l = ProxySelector.getDefault().select(new URI(url));
        } catch (URISyntaxException e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (l != null) {
            for (Proxy proxy : l) {
                sb.append("proxy type: " + proxy.type() + '\n');

                InetSocketAddress addr = (InetSocketAddress) proxy.address();

                if (addr == null) {
                    sb.append("No Proxy\n");
                } else {
                    sb.append("proxy hostname: " + addr.getHostName() + "\n");
                    sb.append("proxy port: " + addr.getPort() + "\n");
                }
            }
        }

        return new ResponseEntity<String>(sb.toString(), HttpStatus.OK);
    }

    /**
     * Sanity Check JVM sur le contenu du TrustStore chargé par défaut. Utile pour vérifier les
     * certificats racine des autorités de certifications pour nos appels LDAPS, HTTPS avec
     * certificats auto signés ou autres.
     *
     * @return contenu du TrustManager.
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "Retourne le contenu du TrustStore par défaut.", notes = "Permet de vérifier le "
            + "contenu du TrustStore pour les appels HTTP en SSL/TLS et s'il " + "est bien configuré pour le serveur.")
    @RequestMapping(value = "/trustmanager", method = RequestMethod.GET)
    public ResponseEntity trustManager() {

        StringBuilder sb = new StringBuilder();
        sb.append("Default TrustManagers :\n");

        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore) null);

            TrustManager[] tms = trustManagerFactory.getTrustManagers();
            sb.append("\nNumber of TrustedManagers : " + tms.length);
            for (TrustManager trustManager : tms) {
                if (trustManager instanceof X509TrustManager) {
                    X509TrustManager x509tm = (X509TrustManager) trustManager;
                    sb.append("\n\n\tX509TrustManager " + x509tm.toString());
                    sb.append("\n\tNumber of accepted issuers : " + x509tm.getAcceptedIssuers().length);
                    java.security.cert.X509Certificate[] cacerts = x509tm.getAcceptedIssuers();
                    for (java.security.cert.X509Certificate x509Certificate : cacerts) {
                        sb.append("\n\t\t" + x509Certificate.getSubjectX500Principal().toString());
                    }
                } else {
                    sb.append("\n\n\tNot a X509TrustManager " + trustManager.toString());
                }
            }

            return new ResponseEntity<>(sb.toString(), HttpStatus.OK);

        } catch (NoSuchAlgorithmException e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (KeyStoreException e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Sanity Check JVM sur le contenu vu par le serveur quand il reçoit une requete HTTP.
     *
     * @return contenu de la requête.
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "Retourne le contenu de la requête telle que vue par le serveur .", notes = "Permet de "
            + "vérifier le contenu des requêtes HTTP recçu dans les controllers Spring de l'application, "
            + "par exemple pour mesurer l'impact du proxy sur les headers...")
    @RequestMapping(value = "/echo", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> echo(HttpServletRequest request) {

        final StringBuilder sb = new StringBuilder();

        sb.append("AuthType = " + request.getAuthType() + "\n");
        sb.append("CharacterEncoding = " + request.getCharacterEncoding() + "\n");
        sb.append("ContentLength = " + request.getContentLength() + "\n");
        sb.append("ContentType = " + request.getContentType() + "\n");
        sb.append("ContextPath = " + request.getContextPath() + "\n");

        // headers
        Enumeration<String> hNames = request.getHeaderNames();
        sb.append("Headers : [ \n");
        while (hNames != null && hNames.hasMoreElements()) {
            String h = hNames.nextElement();
            Enumeration<String> hValues = request.getHeaders(h);
            sb.append("\t" + h + " : [");
            while (hValues.hasMoreElements()) {
                sb.append(hValues.nextElement() + (hValues.hasMoreElements() ? ", " : ""));
            }
            sb.append("]" + (hNames.hasMoreElements() ? "," : "") + "\n");
        }
        sb.append("]\n");

        sb.append("LocalAddr = " + request.getLocalAddr() + "\n");
        sb.append("LocalName = " + request.getLocalName() + "\n");
        sb.append("LocalPort = " + request.getLocalPort() + "\n");
        sb.append("Method = " + request.getMethod() + "\n");
        sb.append("PathInfo = " + request.getPathInfo() + "\n");

        // parameters
        Enumeration<String> pNames = request.getParameterNames();
        sb.append("Parameters : [ \n");
        while (pNames != null && pNames.hasMoreElements()) {
            String p = pNames.nextElement();

            sb.append("\t" + p + " : " + request.getParameter(p) + (pNames.hasMoreElements() ? "," : "") + "\n");
        }
        sb.append("]\n");

        sb.append("PathTranslated = " + request.getPathTranslated() + "\n");
        sb.append("Protocol = " + request.getProtocol() + "\n");
        sb.append("QueryString = " + request.getQueryString() + "\n");
        sb.append("RemoteAddr = " + request.getRemoteAddr() + "\n");
        sb.append("RemoteHost = " + request.getRemoteHost() + "\n");
        sb.append("RemotePort = " + request.getRemotePort() + "\n");
        sb.append("RemoteUser = " + request.getRemoteUser() + "\n");
        sb.append("RequestedSessionId = " + request.getRequestedSessionId() + "\n");
        sb.append("RequestURI = " + request.getRequestURI() + "\n");
        sb.append("Scheme = " + request.getScheme() + "\n");
        sb.append("ServerName = " + request.getServerName() + "\n");
        sb.append("ServerPort = " + request.getServerPort() + "\n");
        sb.append("ServletPath = " + request.getServletPath() + "\n");

        // Cookies
        Cookie[] cookies = request.getCookies();
        sb.append("Cookies : [\n");
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                sb.append("\tname : " + cookie.getName() + "\n");
                sb.append("\t\tdomain : " + cookie.getDomain() + "\n");
                sb.append("\t\tmaxAge : " + cookie.getMaxAge() + "\n");
                sb.append("\t\tpath : " + cookie.getPath() + "\n");
                sb.append("\t\tsecure : " + cookie.getSecure() + "\n");
                sb.append("\t\tvalue : " + cookie.getValue() + "\n");
                sb.append("\t\tversion : " + cookie.getVersion() + "\n");
            }
        } else {
            sb.append("NULL");
        }
        sb.append("]\n");

        // Locales
        Enumeration<Locale> locales = request.getLocales();
        sb.append("Locales : [ \n");
        while (locales != null && locales.hasMoreElements()) {
            Locale l = locales.nextElement();

            sb.append("\t" + l + (locales.hasMoreElements() ? "," : "") + "\n");
        }
        sb.append("]\n");
        sb.append("Locale = " + request.getLocale() + "\n");
        sb.append("RequestURL = " + request.getRequestURL() + "\n");
        sb.append("Session(false) = " + request.getSession(false) + "\n");
        sb.append("UserPrincipal = " + request.getUserPrincipal() + "\n");

        return new ResponseEntity<>(sb.toString(), HttpStatus.OK);
    }
}
