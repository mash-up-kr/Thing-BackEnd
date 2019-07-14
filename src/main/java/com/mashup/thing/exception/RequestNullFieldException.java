package com.mashup.thing.exception;

import org.springframework.http.HttpStatus;

public class RequestNullFieldException extends BaseException {
    public RequestNullFieldException() {
        this(HttpStatus.BAD_REQUEST);
    }

    public RequestNullFieldException(HttpStatus httpStatus) {
        this(4006, httpStatus);
    }

    public RequestNullFieldException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .errorCode(code)
                .httpStatus(httpStatus)
                .msg("Request Null Field - Check Request Field")
                .build());
    }
}
