package com.hs.selab.error.exception.member;

import com.hs.selab.error.dto.ErrorMessage;
import com.hs.selab.error.exception.BusinessException;

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
