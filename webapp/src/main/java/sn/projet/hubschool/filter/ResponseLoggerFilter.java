package sn.projet.hubschool.filter;

import sn.projet.hubschool.filter.util.MyHttpServletResponseWrapper;

import org.apache.commons.io.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * Logue les appels des webservices ainsi que les parametres et les jsons qui leur sont passes
 */
@Component
public class ResponseLoggerFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseLoggerFilter.class);

    private static final String ETOILES = " ************************************************************** ";

    @Override
    public void init(FilterConfig config) throws ServletException {
        // NOOP.
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException,
            IOException {
        if (response.getCharacterEncoding() == null) {
            response.setCharacterEncoding(Charsets.UTF_8.name());
        }

        MyHttpServletResponseWrapper responseCopier = new MyHttpServletResponseWrapper((HttpServletResponse) response);

        try {
            chain.doFilter(request, responseCopier);
            responseCopier.flushBuffer();
        } finally {
            byte[] copy = responseCopier.getCopy();
            String responseStr = new String(copy, response.getCharacterEncoding());

            String separator = System.getProperty("line.separator");
            LOGGER.debug(separator + ETOILES + separator
                    + " ****************** DEBUT REPONSE JSON ************************ " + separator + ETOILES);
            LOGGER.info(separator + response.getContentType() + " " + responseStr.length() + " chars " + separator
                    + separator + responseStr + separator);
            LOGGER.debug(separator + ETOILES + separator
                    + " ******************* FIN REPONSE JSON ************************* " + separator + ETOILES);
        }
    }

    @Override
    public void destroy() {
        // NOOP.
    }

}
