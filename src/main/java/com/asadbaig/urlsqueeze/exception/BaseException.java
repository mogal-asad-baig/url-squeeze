package com.asadbaig.urlsqueeze.exception;

import com.asadbaig.urlsqueeze.model.enums.ResponseCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

@NoArgsConstructor
@Getter
public class BaseException extends RuntimeException{

    ResponseCode errorCode;
    String errorDescription;

    public BaseException(ResponseCode errorCode, String errorDescription) {
        super(errorDescription);
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }
}
