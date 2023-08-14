package com.core.service.error.exception.member;

import com.core.service.error.dto.ErrorMessage;
import com.core.service.error.exception.BusinessException;

public class MalformedJwtException extends BusinessException {
    public MalformedJwtException(ErrorMessage message) {
        super(message);
    }

    public MalformedJwtException(ErrorMessage message, String reason) {
        super(message, reason);
    }

    public MalformedJwtException(String reason) {
        super(reason);
    }
}
