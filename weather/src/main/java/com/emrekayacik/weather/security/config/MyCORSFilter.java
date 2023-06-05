/**
 * Custom CORS filter to handle Cross-Origin Resource Sharing (CORS) configuration.
 */
package com.emrekayacik.weather.security.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MyCORSFilter implements Filter {

    /**
     * Filters the incoming requests and adds the necessary CORS headers to the response.
     *
     * @param req   The {@link ServletRequest} object.
     * @param res   The {@link ServletResponse} object.
     * @param chain The {@link FilterChain} object.
     * @throws IOException      If an I/O error occurs during the processing of the filter chain.
     * @throws ServletException If an exception occurs during the processing of the filter chain.
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me, Authorization");

        chain.doFilter(req, res);
    }

    /**
     * Initializes the filter. This method is empty as no initialization is required for this filter.
     *
     * @param filterConfig The {@link FilterConfig} object.
     */
    @Override
    public void init(FilterConfig filterConfig) {
    }

    /**
     * Cleans up any resources used by the filter. This method is empty as no cleanup is required for this filter.
     */
    @Override
    public void destroy() {
    }

}
