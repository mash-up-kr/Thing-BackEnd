package com.mashup.thing.recommendation.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResVideoDto {
    private Long id;
    private String thumbnail;
    private String youtubeVideoId;
    private String title;
}
