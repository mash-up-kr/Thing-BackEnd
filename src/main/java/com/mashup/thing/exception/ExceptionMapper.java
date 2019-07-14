package com.mashup.thing.exception;

import com.mashup.thing.exception.dto.ResErrorDto;
import com.mashup.thing.exception.dto.ResExceptionDto;
import org.springframework.stereotype.Component;

@Component
public class ExceptionMapper {


    public ResExceptionDto toResExceptionDto(ErrorModel errorModel) {
        return ResExceptionDto.builder()
                .error(toResErrorDto(errorModel))
                .build();
    }

    private ResErrorDto toResErrorDto(ErrorModel errorModel) {
        return ResErrorDto.builder()
                .code(errorModel.getCode())
                .massage(errorModel.getMassage())
                .build();
    }
}
