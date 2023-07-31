package tr.org.ji.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tr.org.ji.interceptor.RequestInInterceptor;

@Configuration
public class WebMVCConfiguration  implements WebMvcConfigurer {

    private final RequestInInterceptor requestInInterceptor;

    public WebMVCConfiguration(RequestInInterceptor requestInInterceptor) {
        this.requestInInterceptor = requestInInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestInInterceptor);
    }
}
