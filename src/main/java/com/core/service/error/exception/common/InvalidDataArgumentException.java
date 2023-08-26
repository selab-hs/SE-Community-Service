package com.core.service.error.exception.common;

import com.core.service.error.dto.ErrorMessage;
import com.core.service.error.exception.BusinessException;

public class InvalidDataArgumentException extends BusinessException {
    public InvalidDataArgumentException(ErrorMessage message) {
        super(message);
    }

    public InvalidDataArgumentException(ErrorMessage message, String reason) {
        super(message, reason);
    }

    public InvalidDataArgumentException(String reason) {
        super(reason);
    }
}
