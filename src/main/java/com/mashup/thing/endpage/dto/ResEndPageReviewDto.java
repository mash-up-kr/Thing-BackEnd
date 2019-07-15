package com.mashup.thing.endpage.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ResEndPageReviewDto {

    private Long id;
    private LocalDateTime createAt;
    private String nickName;
    private String profileUrl;
    private String text;
    private String liked;
    private Boolean owner;
}
