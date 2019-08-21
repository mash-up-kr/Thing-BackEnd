package com.mashup.thing.exception.category;

import com.mashup.thing.exception.BaseException;
import com.mashup.thing.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class NotFoundCategoryTypeException extends BaseException {
    public NotFoundCategoryTypeException() {
        this(HttpStatus.BAD_REQUEST);
    }

    public NotFoundCategoryTypeException(HttpStatus httpStatus) {
        this(4007, httpStatus);
    }

    public NotFoundCategoryTypeException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .code(code)
                .httpStatus(httpStatus)
                .massage("Not Found CategoryType")
                .build());
    }
}
