package com.mashup.thing.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Builder
@Getter
public class ErrorModel {
    private int errorCode;
    private HttpStatus httpStatus;
    private String msg;
}
