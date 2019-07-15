package com.mashup.thing.endpage.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class ResEndPageDto {

    private Long id;
    private String name;
    private String channelId;
    private String description;
    private String bannerImgUrl;
    private LocalDateTime publishedAt;
    private String thumbnail;
    private Long subscriberCount;
    private Long viewCount;
    private Long likeReviewCount;
    private Long noReviewCount;
    private List<ResEndPageReviewDto> likeReviews;
    private List<ResEndPageReviewDto> noReviews;
    private List<ResEndPageVideoDto> videos;

}
