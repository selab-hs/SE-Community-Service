package com.hs.selab.config.security;

import com.hs.selab.auth.filter.JwtAuthenticationFilter;
import com.hs.selab.auth.token.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
@RequiredArgsConstructor
public class SecurityFilterConfig {
    private final TokenProvider tokenProvider;

    @Bean
    public FilterRegistrationBean<Filter> JwtAuthenticationCheckFilter() {
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new JwtAuthenticationFilter(tokenProvider));
        filterFilterRegistrationBean.setOrder(1);
        filterFilterRegistrationBean.addUrlPatterns("/*");

        return filterFilterRegistrationBean;
    }
}
