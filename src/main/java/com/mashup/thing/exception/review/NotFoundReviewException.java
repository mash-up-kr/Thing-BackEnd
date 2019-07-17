package com.mashup.thing.exception.review;

import com.mashup.thing.exception.BaseException;
import com.mashup.thing.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class NotFoundReviewException extends BaseException {
    public NotFoundReviewException() {
        this(HttpStatus.BAD_REQUEST);
    }

    public NotFoundReviewException(HttpStatus httpStatus) {
        this(4006, httpStatus);
    }

    public NotFoundReviewException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .code(code)
                .httpStatus(httpStatus)
                .massage("Not Found Review")
                .build());
    }
}
