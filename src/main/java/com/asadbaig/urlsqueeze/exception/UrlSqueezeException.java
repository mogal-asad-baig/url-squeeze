package com.asadbaig.urlsqueeze.exception;

import com.asadbaig.urlsqueeze.model.enums.ResponseCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UrlSqueezeException extends BaseException{
    public UrlSqueezeException(ResponseCode errorCode, String errorDescription) {
       super(errorCode, errorDescription);
    }
}
