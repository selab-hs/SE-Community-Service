package com.core.service.config.web;

import com.core.service.auth.resolver.AuthArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private  final AuthArgumentResolver authArgumentResolver;

    @Bean
    @ConditionalOnMissingBean(UrlBasedCorsConfigurationSource.class)
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        var corsConfig = new CorsConfiguration();

        corsConfig.addAllowedOriginPattern(CorsConfiguration.ALL);
        corsConfig.addAllowedHeader(CorsConfiguration.ALL);
        corsConfig.addAllowedMethod(CorsConfiguration.ALL);

        corsConfig.setAllowCredentials(true);
        corsConfig.setMaxAge(3600L);

        var corsConfigSource = new UrlBasedCorsConfigurationSource();
        corsConfigSource.registerCorsConfiguration("/**", corsConfig);
        return corsConfigSource;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(authArgumentResolver);
    }
}
