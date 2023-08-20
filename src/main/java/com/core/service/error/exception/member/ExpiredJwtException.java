package com.core.service.error.exception.member;

import com.core.service.error.dto.ErrorMessage;
import com.core.service.error.exception.BusinessException;

public class ExpiredJwtException extends BusinessException {
    public ExpiredJwtException(ErrorMessage message) {
        super(message);
    }

    public ExpiredJwtException(ErrorMessage message, String reason) {
        super(message, reason);
    }

    public ExpiredJwtException(String reason) {
        super(reason);
    }
}
