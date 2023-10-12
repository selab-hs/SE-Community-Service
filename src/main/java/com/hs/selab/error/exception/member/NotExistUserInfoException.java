package com.hs.selab.error.exception.member;

import com.hs.selab.error.dto.ErrorMessage;
import com.hs.selab.error.exception.BusinessException;

public class NotExistUserInfoException extends BusinessException {
    public NotExistUserInfoException(ErrorMessage message) {
        super(message);
    }

    public NotExistUserInfoException(ErrorMessage message, String reason) {
        super(message, reason);
    }

    public NotExistUserInfoException(String reason) {
        super(reason);
    }
}
