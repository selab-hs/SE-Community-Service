package com.core.service.error.exception.member;

import com.core.service.error.dto.ErrorMessage;
import com.core.service.error.exception.BusinessException;

public class UnauthorizedAccessException extends BusinessException {

    public UnauthorizedAccessException(ErrorMessage message) {
        super(message);
    }

    public UnauthorizedAccessException(ErrorMessage message, String reason) {
        super(message, reason);
    }

    public UnauthorizedAccessException(String reason) {
        super(reason);
    }
}
