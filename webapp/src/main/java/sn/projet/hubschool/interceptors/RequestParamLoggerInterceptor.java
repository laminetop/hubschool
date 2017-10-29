package sn.projet.hubschool.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Logue la reponse retournee au front par les webservices
 */
public class RequestParamLoggerInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestParamLoggerInterceptor.class);

    private static final String SEPARATOR_LINE = System.getProperty("line.separator")
            //+ " ************************************************************** "
            ;

    /**
     * Logue la reponse retournee au front par les webservices
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param handler  Object
     * @return boolean
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        @SuppressWarnings("unchecked")
        Map<String, String[]> params = (Map<String, String[]>) request.getParameterMap();
        StringBuilder stringBuilder = new StringBuilder("");

        LOGGER.debug(SEPARATOR_LINE + " ****************** DEBUT REQUETE JSON ************************ " + SEPARATOR_LINE);
        stringBuilder = stringBuilder.append(request.getMethod() + " : " + request.getRequestURI() + " --- " + SEPARATOR_LINE);


        for (String paramName : params.keySet()) {
            stringBuilder = stringBuilder.append(" --- PARAM " + paramName + " : " + SEPARATOR_LINE);
            for (String paramValue : params.get(paramName)) {
                stringBuilder = stringBuilder.append(paramValue).append(SEPARATOR_LINE);
            }
        }

        if (LOGGER.isDebugEnabled()) {
            stringBuilder = stringBuilder.append("\"headers\"" + " { " + SEPARATOR_LINE);
            Enumeration<String> headerNames = request.getHeaderNames();

            while (headerNames.hasMoreElements()) {
                String key = headerNames.nextElement();
                String value = request.getHeader(key);
                stringBuilder = stringBuilder.append("  \"" + key + "\"" + " : " + "\"" + value + "\""
                        + (headerNames.hasMoreElements() ? "," : "") + SEPARATOR_LINE);
            }

            stringBuilder = stringBuilder.append("}" + SEPARATOR_LINE);
        }

        LOGGER.info(stringBuilder.toString());
        LOGGER.debug(SEPARATOR_LINE + " ******************* FIN REQUETE JSON ************************* " + SEPARATOR_LINE);

        return super.preHandle(request, response, handler);
    }
}
