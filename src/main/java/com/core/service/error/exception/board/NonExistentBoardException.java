package com.core.service.error.exception.board;

import com.core.service.error.dto.ErrorMessage;
import com.core.service.error.exception.BusinessException;

public class NonExistentBoardException extends BusinessException {

    public NonExistentBoardException(ErrorMessage message) {
        super(message);
    }

    public NonExistentBoardException(ErrorMessage message, String reason) {
        super(message, reason);
    }

    public NonExistentBoardException(String reason) {
        super(reason);
    }
}
