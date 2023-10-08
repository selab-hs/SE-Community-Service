package com.hs.selab.error.exception.member;

import com.hs.selab.error.dto.ErrorMessage;
import com.hs.selab.error.exception.BusinessException;

public class NotExistMemberException extends BusinessException {
    public NotExistMemberException(ErrorMessage message) {
        super(message);
    }

    public NotExistMemberException(ErrorMessage message, String reason) {
        super(message, reason);
    }

    public NotExistMemberException(String reason) {
        super(reason);
    }
}
