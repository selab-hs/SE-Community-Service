package com.hs.selab.error.exception.member;

import com.hs.selab.error.dto.ErrorMessage;
import com.hs.selab.error.exception.BusinessException;

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
