package com.hs.selab.auth.infrastructure.authentication;

import com.hs.selab.auth.domain.Authentication;
import com.hs.selab.auth.infrastructure.LocalContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class DefaultAuthenticationStrategy implements GetAuthenticationStrategy, SetAuthenticationStrategy{
    @Override
    public Authentication get(HttpServletRequest request) {
        return LocalContextHolder.getContext();
    }

    @Override
    public void set(Authentication authentication) {
        LocalContextHolder.setContext(authentication);
    }
}
