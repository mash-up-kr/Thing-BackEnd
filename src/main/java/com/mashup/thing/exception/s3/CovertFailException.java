package com.mashup.thing.exception.s3;

import com.mashup.thing.exception.BaseException;
import com.mashup.thing.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class CovertFailException extends BaseException {

    public CovertFailException() {
        this(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public CovertFailException(HttpStatus httpStatus) {
        this(5001, httpStatus);
    }

    public CovertFailException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .errorCode(code)
                .httpStatus(httpStatus)
                .msg("Covert Fail File")
                .build());
    }
}
