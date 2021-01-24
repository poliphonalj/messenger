package org.progmatic.messenger.Filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.servlet.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;


@Component
public class ParamFilter implements Filter {
    private static Logger log;

    @Override
    public void init(FilterConfig filterConfig)
            throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setContentType("text/html; charset=UTF-8");
        System.out.println("blaf");
        log = LoggerFactory.getLogger(ParamFilter.class);


        for (Map.Entry actualEntry : servletRequest.getParameterMap().entrySet()) {
            if (actualEntry.getValue() instanceof String[]) {
                System.out.println("params: " + actualEntry.getKey() + " " + Arrays.toString((String[]) actualEntry.getValue()));
            } else {
                System.out.println(actualEntry.getKey());
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }

}



