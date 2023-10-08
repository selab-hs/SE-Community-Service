package com.hs.selab.error.exception.member;

import com.hs.selab.error.dto.ErrorMessage;
import com.hs.selab.error.exception.BusinessException;

public class InvalidPasswordMatchException extends BusinessException {
    public InvalidPasswordMatchException(ErrorMessage message) {
        super(message);
    }

    public InvalidPasswordMatchException(ErrorMessage message, String reason) {
        super(message, reason);
    }

    public InvalidPasswordMatchException(String reason) {
        super(reason);
    }
}
