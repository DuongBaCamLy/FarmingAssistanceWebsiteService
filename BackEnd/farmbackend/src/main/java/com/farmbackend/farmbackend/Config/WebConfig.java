// package com.farmbackend.farmbackend.Config;
package com.farmbackend.farmbackend.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")  // Cho phép tất cả các domain
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Cho phép các phương thức HTTP
                .allowedHeaders("*");  // Cho phép tất cả các headers
    }
}