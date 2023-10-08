package com.hs.selab.error.exception.member;

import com.hs.selab.error.dto.ErrorMessage;
import com.hs.selab.error.exception.BusinessException;

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
