package com.asadbaig.urlsqueeze.exception;

import com.asadbaig.urlsqueeze.model.ErrorResponse;
import com.asadbaig.urlsqueeze.model.enums.ResponseCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UrlSqueezeException.class)
    public ResponseEntity<ErrorResponse> handleUrlSqueezeException(UrlSqueezeException exception) {
        ErrorResponse errorResponse = buildErrorResponse(exception);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), ResponseCode.MF400.name(), ResponseCode.MF400.getDescription());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private ErrorResponse buildErrorResponse(BaseException exception) {
        return ErrorResponse.builder()
                .generatedTime(LocalDateTime.now())
                .errorCode(exception.getErrorCode().name())
                .errorDescription(exception.getErrorCode().getDescription())
                .build();
    }
}
