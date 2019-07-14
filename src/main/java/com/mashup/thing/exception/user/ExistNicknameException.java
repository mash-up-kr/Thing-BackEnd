package com.mashup.thing.exception.user;

import com.mashup.thing.exception.BaseException;
import com.mashup.thing.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class ExistNicknameException extends BaseException {

    public ExistNicknameException() {
        this(HttpStatus.CONFLICT);
    }

    public ExistNicknameException(HttpStatus httpStatus) {
        this(4901, httpStatus);
    }

    public ExistNicknameException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .code(code)
                .httpStatus(httpStatus)
                .massage("Exist Nickname")
                .build());
    }
}
