package com.mashup.thing.exception.user;

import com.mashup.thing.exception.BaseException;
import com.mashup.thing.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class NotFoundUserException extends BaseException {

    public NotFoundUserException() {
        this(HttpStatus.BAD_REQUEST);
    }

    public NotFoundUserException(HttpStatus httpStatus) {
        this(4002, httpStatus);
    }

    public NotFoundUserException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .errorCode(code)
                .httpStatus(httpStatus)
                .msg("Not Found User - Invalid Id or Uid")
                .build());
    }
}

