package com.hs.selab.error;

import com.hs.selab.error.dto.ErrorMessage;
import com.hs.selab.error.dto.ErrorResponseDto;
import com.hs.selab.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponseDto> handleBusinessException(BusinessException e) {
        var errorMessage = e.getErrorMessage();

        log.error("[ERROR] BusinessException -> {}", errorMessage.getMessage());

        return ErrorResponseDto.of(errorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        var errorMessage = ErrorMessage.INVALID_REQUEST_PARAMETER;

        log.error("[ERROR] MethodArgumentNotValidException -> {}", e.getBindingResult());

        return ErrorResponseDto.of(errorMessage);
    }
}
