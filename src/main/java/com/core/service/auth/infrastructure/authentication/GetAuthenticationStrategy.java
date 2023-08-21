package com.core.service.auth.infrastructure.authentication;

import com.core.service.auth.domain.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface GetAuthenticationStrategy {
    Authentication get(HttpServletRequest request);
}

