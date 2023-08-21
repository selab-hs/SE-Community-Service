package com.core.service.error.exception.member;

import com.core.service.error.dto.ErrorMessage;
import com.core.service.error.exception.BusinessException;

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
