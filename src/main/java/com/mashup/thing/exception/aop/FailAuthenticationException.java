package com.mashup.thing.exception.aop;

import com.mashup.thing.exception.BaseException;
import com.mashup.thing.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class FailAuthenticationException extends BaseException {

    public FailAuthenticationException() {
        this(HttpStatus.FORBIDDEN);
    }

    public FailAuthenticationException(HttpStatus httpStatus) {
        this(4001, httpStatus);
    }

    public FailAuthenticationException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .errorCode(code)
                .httpStatus(httpStatus)
                .msg("Fail Authentication - Invalid Uid")
                .build());
    }
}
