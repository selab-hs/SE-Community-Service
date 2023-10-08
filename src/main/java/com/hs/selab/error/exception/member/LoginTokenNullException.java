package com.hs.selab.error.exception.member;

import com.hs.selab.error.dto.ErrorMessage;
import com.hs.selab.error.exception.BusinessException;

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
