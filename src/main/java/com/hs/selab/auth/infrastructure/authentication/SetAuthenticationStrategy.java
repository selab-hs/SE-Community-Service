package com.hs.selab.auth.infrastructure.authentication;

import com.hs.selab.auth.domain.Authentication;

public interface SetAuthenticationStrategy {
    void set(Authentication authentication);
}
