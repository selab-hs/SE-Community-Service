package com.hs.selab.error.exception.member;

import com.hs.selab.error.dto.ErrorMessage;
import com.hs.selab.error.exception.BusinessException;

public class InvalidNameException extends BusinessException {
    public InvalidNameException(ErrorMessage message) {
        super(message);
    }

    public InvalidNameException(ErrorMessage message, String reason) {
        super(message, reason);
    }

    public InvalidNameException(String reason) {
        super(reason);
    }
}
