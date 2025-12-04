package pe.edu.utp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/envios/**", "/rutas/**", "/cargas/**", "/trazabilidad/**", "/api")
                .excludePathPatterns("/login", "/logout", "/css/**", "/js/**", "/images/**", "/webjars/**");
    }
}
