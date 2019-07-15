package com.mashup.thing.user.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ResSearchDto {
    private Long id;
    private String text;
    private LocalDateTime createAt;

}
