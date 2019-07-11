package com.mashup.thing.youtuber.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
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
    private List<ResEndPageReviewDto> likeReviews;
    private List<ResEndPageReviewDto> noReviews;
    private List<ResEndPageVideoDto> videos;

    public ResEndPageDto(Long id, String name, String bannerImgUrl,
                         String channelId, String description,
                         LocalDateTime publishedAt, Long subscriberCount,
                         String thumbnail, Long viewCount,
                         List<ResEndPageVideoDto> resEndPageVideos,
                         List<ResEndPageReviewDto> resEndPageLikeReviews,
                         List<ResEndPageReviewDto> resEndPageNoReviews) {
        this.id = id;
        this.name = name;
        this.bannerImgUrl = bannerImgUrl;
        this.channelId = channelId;
        this.description = description;
        this.publishedAt = publishedAt;
        this.subscriberCount = subscriberCount;
        this.thumbnail = thumbnail;
        this.viewCount = viewCount;
        this.likeReviews = resEndPageLikeReviews;
        this.noReviews = resEndPageNoReviews;
        this.videos = resEndPageVideos;
    }
}
