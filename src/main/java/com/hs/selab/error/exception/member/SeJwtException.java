package com.hs.selab.error.exception.member;

import com.hs.selab.error.dto.ErrorMessage;
import com.hs.selab.error.exception.BusinessException;

public class SeJwtException extends BusinessException {
    public SeJwtException(ErrorMessage message){
        super(message);
    }

    public SeJwtException(ErrorMessage message, String reason) {
        super(message, reason);
    }

    public SeJwtException(String reason) {
        super(reason);
    }
}
