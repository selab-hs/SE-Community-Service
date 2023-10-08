package com.hs.selab.error.exception.member;

import com.hs.selab.error.dto.ErrorMessage;
import com.hs.selab.error.exception.BusinessException;

public class InvalidClassNumberException extends BusinessException {
    public InvalidClassNumberException(ErrorMessage message) {
        super(message);
    }

    public InvalidClassNumberException(ErrorMessage message, String reason) {
        super(message, reason);
    }

    public InvalidClassNumberException(String reason) {
        super(reason);
    }
}
