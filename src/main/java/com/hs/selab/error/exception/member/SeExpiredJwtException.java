package com.hs.selab.error.exception.member;

import com.hs.selab.error.dto.ErrorMessage;
import com.hs.selab.error.exception.BusinessException;

public class SeExpiredJwtException extends BusinessException {
    public SeExpiredJwtException(ErrorMessage message) {
        super(message);
    }

    public SeExpiredJwtException(ErrorMessage message, String reason) {
        super(message, reason);
    }

    public SeExpiredJwtException(String reason) {
        super(reason);
    }
}
