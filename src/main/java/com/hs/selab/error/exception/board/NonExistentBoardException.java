package com.hs.selab.error.exception.board;

import com.hs.selab.error.dto.ErrorMessage;
import com.hs.selab.error.exception.BusinessException;

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
