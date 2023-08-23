package com.core.service.error.exception.member;

import com.core.service.error.dto.ErrorMessage;
import com.core.service.error.exception.BusinessException;

public class LoginTokenNullException extends BusinessException {
    public LoginTokenNullException(ErrorMessage message) {
        super(message);
    }

    public LoginTokenNullException(ErrorMessage message, String reason) {
        super(message, reason);
    }

    public LoginTokenNullException(String reason) {
        super(reason);
    }
}
