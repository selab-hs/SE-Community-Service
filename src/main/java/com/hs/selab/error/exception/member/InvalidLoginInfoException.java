package com.hs.selab.error.exception.member;

import com.hs.selab.error.dto.ErrorMessage;
import com.hs.selab.error.exception.BusinessException;

public class InvalidLoginInfoException extends BusinessException {
    public InvalidLoginInfoException(ErrorMessage message) {
        super(message);
    }

    public InvalidLoginInfoException(ErrorMessage message, String reason) {
        super(message, reason);
    }

    public InvalidLoginInfoException(String reason) {
        super(reason);
    }
}
