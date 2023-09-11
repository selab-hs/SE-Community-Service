package com.core.service.error.exception.board;

import com.core.service.error.dto.ErrorMessage;
import com.core.service.error.exception.BusinessException;

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
