package com.hs.selab.error.exception.board;

import com.hs.selab.error.dto.ErrorMessage;
import com.hs.selab.error.exception.BusinessException;

public class FileConverterException extends BusinessException {

    public FileConverterException(ErrorMessage message) {
        super(message);
    }

    public FileConverterException(ErrorMessage message, String reason) {
        super(message, reason);
    }

    public FileConverterException(String reason) {
        super(reason);
    }
}
