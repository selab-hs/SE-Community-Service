package com.hs.selab.error.exception.common;

import com.hs.selab.error.dto.ErrorMessage;
import com.hs.selab.error.exception.BusinessException;

public class InvalidDataArgumentException extends BusinessException {
    public InvalidDataArgumentException(ErrorMessage message) {
        super(message);
    }

    public InvalidDataArgumentException(ErrorMessage message, String reason) {
        super(message, reason);
    }

    public InvalidDataArgumentException(String reason) {
        super(reason);
    }
}
