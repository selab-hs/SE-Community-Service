package com.core.service.error.exception.member;

import com.core.service.error.dto.ErrorMessage;
import com.core.service.error.exception.BusinessException;

public class InvalidClassNumberException extends BusinessException {
    public InvalidClassNumberException(ErrorMessage message) {
        super(message);
    }

    public InvalidClassNumberException(ErrorMessage message, String reason) {
        super(message, reason);
    }

    public InvalidClassNumberException(String reason) {
        super(reason);
    }
}
