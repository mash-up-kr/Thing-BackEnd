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
                .code(code)
                .httpStatus(httpStatus)
                .massage("Covert Fail File")
                .build());
    }
}
