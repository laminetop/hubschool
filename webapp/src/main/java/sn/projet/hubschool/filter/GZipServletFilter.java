package sn.projet.hubschool.filter;

import sn.projet.hubschool.filter.util.GZipServletResponseWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * GZipServletFilter. <p> Permet d'activer l'encoding GZip sur les reponses HTTP si dans la requête
 * le client a indique en header "Accept-encoding: gzip". <p> L'encodage permet de reduire la
 * volumetrie des echanges, et du coup d'accelerer les temps de traitement, car le temps consommer
 * pour zipper puis transmettre le message zippe est inferieur au temps utilise pour transmettre la
 * requete non zippee.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GZipServletFilter implements Filter {

    private static final String GZIP = "gzip";

    private static final String HTTP_RESPONSE_HEADER_CONTENT_ENCODING = "Content-Encoding";

    private static final String HTTP_REQUEST_HEADER_ACCEPT_ENCODING = "Accept-Encoding";

    private static final Logger LOG = LoggerFactory.getLogger(GZipServletFilter.class);

    @Autowired
    private Environment env;

    //@Value("${hubschool.api.gzip.enable}")
    private Optional<String> getGzipEnable = Optional.empty();

    /**
     * initialisation du Filtre. Peut être desactive si on a mis la clef
     * hubschool.api.gzip.enable=false
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.debug("DEBUT : initialisation du GZipServletFilter.");


        LOG.info("GZIP activé ? " + getGzipEnable.orElse("false"));
        LOG.debug("FIN : initialisation du GZipServletFilter.");
    }

    /**
     * callback précédant la destruction du filtre par le serveur web.
     */
    @Override
    public void destroy() {
        /**
         * Il n'y a rien à détruire.
         */
    }

    /**
     * Exécution du filtre qui encode la reponse HTTP en GZIP si la requete accepte cet enconding.
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {

        if (Boolean.valueOf(getGzipEnable.orElse("false"))) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;

            if (acceptsGZipEncoding(httpRequest)) {
                httpResponse.addHeader(HTTP_RESPONSE_HEADER_CONTENT_ENCODING, GZIP);
                GZipServletResponseWrapper gzipResponse = new GZipServletResponseWrapper(httpResponse);
                chain.doFilter(request, gzipResponse);
                gzipResponse.close();
            } else {
                chain.doFilter(request, response);
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    private boolean acceptsGZipEncoding(HttpServletRequest httpRequest) {
        String acceptEncoding = httpRequest.getHeader(HTTP_REQUEST_HEADER_ACCEPT_ENCODING);

        return acceptEncoding != null && acceptEncoding.indexOf(GZIP) != -1;
    }
}
