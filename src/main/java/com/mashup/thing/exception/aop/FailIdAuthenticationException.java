package com.mashup.thing.exception.aop;

import com.mashup.thing.exception.BaseException;
import com.mashup.thing.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class FailIdAuthenticationException extends BaseException {
    public FailIdAuthenticationException() {
        this(HttpStatus.FORBIDDEN);
    }

    public FailIdAuthenticationException(HttpStatus httpStatus) {
        this(4302, httpStatus);
    }

    public FailIdAuthenticationException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .code(code)
                .httpStatus(httpStatus)
                .massage("Fail Authentication - Invalid Id")
                .build());
    }
}
