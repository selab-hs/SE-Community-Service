package com.hs.selab.error.exception.member;

import com.hs.selab.error.dto.ErrorMessage;
import com.hs.selab.error.exception.BusinessException;

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
