package com.core.service.error.exception.member;

import com.core.service.error.dto.ErrorMessage;
import com.core.service.error.exception.BusinessException;

public class InvalidPasswordMatchException extends BusinessException {
    public InvalidPasswordMatchException(ErrorMessage message) {
        super(message);
    }

    public InvalidPasswordMatchException(ErrorMessage message, String reason) {
        super(message, reason);
    }

    public InvalidPasswordMatchException(String reason) {
        super(reason);
    }
}