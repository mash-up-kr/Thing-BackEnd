package com.mashup.thing.youtuber.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ResRecommendationDto {
    private Integer total;
    private List<ResYouTuberDto> youTubers;

    public ResRecommendationDto() {
        this.total = 0;
        this.youTubers = new ArrayList<>();
    }

    public ResRecommendationDto(List<ResYouTuberDto> youTubers) {
        this.total = youTubers.size();
        this.youTubers = youTubers;
    }
}
