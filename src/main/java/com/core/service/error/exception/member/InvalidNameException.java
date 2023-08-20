package com.core.service.error.exception.member;

import com.core.service.error.dto.ErrorMessage;
import com.core.service.error.exception.BusinessException;

public class InvalidNameException extends BusinessException {
    public InvalidNameException(ErrorMessage message) {
        super(message);
    }

    public InvalidNameException(ErrorMessage message, String reason) {
        super(message, reason);
    }

    public InvalidNameException(String reason) {
        super(reason);
    }
}
