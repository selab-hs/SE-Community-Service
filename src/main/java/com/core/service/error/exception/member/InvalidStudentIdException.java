package com.core.service.error.exception.member;

import com.core.service.error.dto.ErrorMessage;
import com.core.service.error.exception.BusinessException;

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
