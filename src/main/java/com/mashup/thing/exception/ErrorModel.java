package com.mashup.thing.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Builder
@Getter
public class ErrorModel {
    private int code;
    private HttpStatus httpStatus;
    private String massage;
}
