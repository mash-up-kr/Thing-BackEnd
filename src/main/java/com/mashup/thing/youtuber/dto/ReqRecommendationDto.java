package com.mashup.thing.youtuber.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReqRecommendationDto {

    private List<String> common;
    private List<String> category;
}
