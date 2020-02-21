package org.tools.ppmtool.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApplicationWebMvcConfiguration implements WebMvcConfigurer {
    public static final String URL_API_BASE = "/api";
    private static final long MAX_AGE_SECS = 3600;

    @Override
    public void addCorsMappings(final CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*")
                .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE").maxAge(MAX_AGE_SECS);
    }

    @Override
    public void configurePathMatch(final PathMatchConfigurer configurer) {
        configurer.addPathPrefix(URL_API_BASE, HandlerTypePredicate.forAnnotation(RestController.class));
    }

}