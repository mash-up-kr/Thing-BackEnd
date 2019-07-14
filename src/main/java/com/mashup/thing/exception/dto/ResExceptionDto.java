package com.mashup.thing.exception.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResExceptionDto {

    private ResErrorDto error;
}
