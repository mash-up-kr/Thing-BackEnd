package com.mashup.thing.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BaseException extends RuntimeException{

    private ErrorModel errorModel;

    protected BaseException(ErrorModel errorModel) {
        super(errorModel.getMsg());
        this.errorModel = errorModel;
    }
}
