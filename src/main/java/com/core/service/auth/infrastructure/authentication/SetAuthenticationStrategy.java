package com.core.service.auth.infrastructure.authentication;

import com.core.service.auth.domain.Authentication;

public interface SetAuthenticationStrategy {
    void set(Authentication authentication);
}
