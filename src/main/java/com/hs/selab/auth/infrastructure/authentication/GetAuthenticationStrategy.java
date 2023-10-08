package com.hs.selab.auth.infrastructure.authentication;

import com.hs.selab.auth.domain.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface GetAuthenticationStrategy {
    Authentication get(HttpServletRequest request);
}

