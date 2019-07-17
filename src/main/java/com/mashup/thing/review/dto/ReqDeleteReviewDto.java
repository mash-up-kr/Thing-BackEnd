package com.mashup.thing.review.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ReqDeleteReviewDto {

    @NotNull
    private Long userId;
    @NotNull
    private Long youTuberId;
}
