package tr.org.ji.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Component
public class RequestInInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(RequestInInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("Request interceptor started: {} {}" , request.getRequestURI(),request.getMethod());
        request.setAttribute("startDate", new Date());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("Request interceptor finished: {} {}" , request.getRequestURI(),request.getMethod());
        Date startDate = (Date) request.getAttribute("startDate");
        Date endDate  = new Date();
        var elapsed = endDate.getTime()-startDate.getTime();
        logger.info("Elapsed time : {} ms" , elapsed);

    }
}
