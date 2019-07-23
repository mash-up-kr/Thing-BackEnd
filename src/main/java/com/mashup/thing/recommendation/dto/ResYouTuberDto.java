package com.mashup.thing.recommendation.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ResYouTuberDto {
    private Long id;
    private String name;
    private String thumbnail;
    private List<String> tag;
    private List<ResVideoDto> videos;
}
