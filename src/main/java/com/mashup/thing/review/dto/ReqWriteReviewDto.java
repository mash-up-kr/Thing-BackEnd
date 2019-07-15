package com.mashup.thing.review.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class ReqWriteReviewDto {
    @NotNull
    private String text;
    @NotNull
    private Integer liked;
    @NotNull
    private Long userId;
}
