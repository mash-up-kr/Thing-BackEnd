package com.mashup.thing.ranking.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResRankingDto {

    private Long id;
    private String name;
    private Long ranking;
    private String viewCount;
    private String subscriberCount;
    private String thumbnail;
    private String bannerImgUrl;
    private String soaring;
}
