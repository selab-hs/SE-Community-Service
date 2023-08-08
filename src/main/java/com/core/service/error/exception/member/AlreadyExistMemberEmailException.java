package com.core.service.error.exception.member;

import com.core.service.error.dto.ErrorMessage;
import com.core.service.error.exception.BusinessException;

public class AlreadyExistMemberEmailException extends BusinessException {

    public AlreadyExistMemberEmailException(ErrorMessage message) {
        super(message);
    }

    public AlreadyExistMemberEmailException(ErrorMessage message, String reason) {
        super(message, reason);
    }

    public AlreadyExistMemberEmailException(String reason) {
        super(reason);
    }
}
