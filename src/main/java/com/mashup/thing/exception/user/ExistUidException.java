package com.mashup.thing.exception.user;

import com.mashup.thing.exception.BaseException;
import com.mashup.thing.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class ExistUidException extends BaseException {

    public ExistUidException() {
        this(HttpStatus.CONFLICT);
    }

    public ExistUidException(HttpStatus httpStatus) {
        this(4004, httpStatus);
    }

    public ExistUidException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .errorCode(code)
                .httpStatus(httpStatus)
                .msg("Exist Uid")
                .build());
    }
}
