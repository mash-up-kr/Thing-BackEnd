package com.mashup.thing.exception.review;

import com.mashup.thing.exception.BaseException;
import com.mashup.thing.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class NotFoundLikedException extends BaseException {
    public NotFoundLikedException() {
        this(HttpStatus.BAD_REQUEST);
    }

    public NotFoundLikedException(HttpStatus httpStatus) {
        this(4005, httpStatus);
    }

    public NotFoundLikedException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .code(code)
                .httpStatus(httpStatus)
                .massage("Not Found Liked Type")
                .build());
    }
}
