package com.mashup.thing.youtuber.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ResHomeDto {
    private List<ResYouTuberDto> recommendedYouTuber;
    private List<ResSoaringYouTuberDto> soaringYouTuber;

    @Getter
    @Builder
    public static class ResSoaringYouTuberDto {

        private Long id;
        private String name;
        private String thumbnail;
        private List<String> tag;
        private Double soaring;

    }


    public ResHomeDto(List<ResYouTuberDto> resYouTuberDtos, List<ResSoaringYouTuberDto> resSoaringYouTuberDtos) {
        this.recommendedYouTuber = resYouTuberDtos;
        this.soaringYouTuber = resSoaringYouTuberDtos;
    }

    public ResHomeDto(List<ResSoaringYouTuberDto> resSoaringYouTuberDtos) {
        this.recommendedYouTuber = new ArrayList<>();
        this.soaringYouTuber = resSoaringYouTuberDtos;
    }
}
