package com.mashup.thing.recommendation.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ResHomeDto {
    private List<ResYouTuberDto> resYouTuberDtos;
    private List<ResSoaringYouTuberDto> resSoaringYouTuberDtos;

    public ResHomeDto(List<ResYouTuberDto> resYouTuberDtos, List<ResSoaringYouTuberDto> resSoaringYouTuberDtos) {
        this.resYouTuberDtos = resYouTuberDtos;
        this.resSoaringYouTuberDtos = resSoaringYouTuberDtos;
    }

    public ResHomeDto(List<ResSoaringYouTuberDto> resSoaringYouTuberDtos) {
        this.resYouTuberDtos = new ArrayList<>();
        this.resSoaringYouTuberDtos = resSoaringYouTuberDtos;
    }
}
