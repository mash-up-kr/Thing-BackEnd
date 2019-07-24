package com.mashup.thing.youtuber.dto;

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


    @Getter
    @Builder
    public static class ResEndPageVideoDto {

        private Long id;
        private String thumbnail;
        private String youtubeVideoId;
        private LocalDateTime publishedAt;
        private String title;

    }

    @Getter
    @Builder
    public static class ResEndPageReviewDto {

        private Long id;
        private LocalDateTime createAt;
        private String nickName;
        private String profileUrl;
        private String text;
        private String liked;
        private Boolean owner;
    }


}
