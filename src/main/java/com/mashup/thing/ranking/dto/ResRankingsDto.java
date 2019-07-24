package com.mashup.thing.ranking.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class ResRankingsDto {
    List<ResRankingDto> rankings;
    private Integer currentPage;
    private Integer nextPage;
    private Integer totalPage;
    private LocalDateTime createAt;
    private String filterType;

    @Getter
    @Builder
    public static class ResRankingDto {

        private Long id;
        private String name;
        private Long ranking;
        private String viewCount;
        private String subscriberCount;
        private String thumbnail;
        private String bannerImgUrl;
        private String soaring;
    }
}
