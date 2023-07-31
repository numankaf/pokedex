package tr.org.ji.filter;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.slf4j.Logger;

@Component
public class RequestInFilter extends CommonsRequestLoggingFilter {
    private final Logger logger = LoggerFactory.getLogger(RequestInFilter.class);

    @Override
    protected boolean shouldLog(HttpServletRequest request) {
        return request.getRequestURI().contains("/users");
    }

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        logger.info("Request filter started: {} {}" , request.getRequestURI(),request.getMethod());
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        logger.info("Request filter finished: {} {}" , request.getRequestURI(),request.getMethod());
    }
}
