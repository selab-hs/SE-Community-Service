package com.hs.selab.error.exception.member;

import com.hs.selab.error.dto.ErrorMessage;
import com.hs.selab.error.exception.BusinessException;

public class InvalidEmailException extends BusinessException {
    public InvalidEmailException(ErrorMessage message) {
        super(message);
    }

    public InvalidEmailException(ErrorMessage message, String reason) {
        super(message, reason);
    }

    public InvalidEmailException(String reason) {
        super(reason);
    }
}
