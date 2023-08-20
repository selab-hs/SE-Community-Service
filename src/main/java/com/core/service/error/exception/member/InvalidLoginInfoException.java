package com.core.service.error.exception.member;

import com.core.service.error.dto.ErrorMessage;
import com.core.service.error.exception.BusinessException;

public class InvalidLoginInfoException extends BusinessException {
    public InvalidLoginInfoException(ErrorMessage message) {
        super(message);
    }

    public InvalidLoginInfoException(ErrorMessage message, String reason) {
        super(message, reason);
    }

    public InvalidLoginInfoException(String reason) {
        super(reason);
    }
}