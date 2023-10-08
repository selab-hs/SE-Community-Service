package com.hs.selab.error.exception.member;

import com.hs.selab.error.dto.ErrorMessage;
import com.hs.selab.error.exception.BusinessException;

public class InvalidPasswordException extends BusinessException {
    public InvalidPasswordException(ErrorMessage message) {
        super(message);
    }

    public InvalidPasswordException(ErrorMessage message, String reason) {
        super(message, reason);
    }

    public InvalidPasswordException(String reason) {
        super(reason);
    }
}
