package com.mashup.thing.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResCategoryDto {
    private Long id;
    private String categoryType;

}
