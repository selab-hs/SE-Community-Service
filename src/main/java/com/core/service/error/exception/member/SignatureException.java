package com.core.service.error.exception.member;

import com.core.service.error.dto.ErrorMessage;
import com.core.service.error.exception.BusinessException;

public class SignatureException extends BusinessException {
    public SignatureException(ErrorMessage message){
        super(message);
    }

    public SignatureException(ErrorMessage message, String reason) {
        super(message, reason);
    }

    public SignatureException(String reason) {
        super(reason);
    }
}
