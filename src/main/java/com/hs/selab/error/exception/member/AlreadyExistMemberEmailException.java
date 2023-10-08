package com.hs.selab.error.exception.member;

import com.hs.selab.error.dto.ErrorMessage;
import com.hs.selab.error.exception.BusinessException;

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
