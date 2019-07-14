package com.mashup.thing.exception.category;

import com.mashup.thing.exception.BaseException;
import com.mashup.thing.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class NotFoundCategoryException extends BaseException {
    public NotFoundCategoryException() {
        this(HttpStatus.BAD_REQUEST);
    }

    public NotFoundCategoryException(HttpStatus httpStatus) {
        this(4005, httpStatus);
    }

    public NotFoundCategoryException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .errorCode(code)
                .httpStatus(httpStatus)
                .msg("Not Found Category - Invalid CategoryId")
                .build());
    }

}
