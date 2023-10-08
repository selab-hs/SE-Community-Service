package com.hs.selab.error.exception.member;

import com.hs.selab.error.dto.ErrorMessage;
import com.hs.selab.error.exception.BusinessException;

public class InvalidStudentIdException extends BusinessException {
    public InvalidStudentIdException(ErrorMessage message) {
        super(message);
    }

    public InvalidStudentIdException(ErrorMessage message, String reason) {
        super(message, reason);
    }

    public InvalidStudentIdException(String reason) {
        super(reason);
    }
}
