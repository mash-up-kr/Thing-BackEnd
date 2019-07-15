package com.mashup.thing.exception.review;

import com.mashup.thing.exception.BaseException;
import com.mashup.thing.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class NotFoundLiked extends BaseException {
    public NotFoundLiked() {
        this(HttpStatus.BAD_REQUEST);
    }

    public NotFoundLiked(HttpStatus httpStatus) {
        this(4005, httpStatus);
    }

    public NotFoundLiked(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .code(code)
                .httpStatus(httpStatus)
                .massage("Not Found Liked Type")
                .build());
    }
}
