package com.mashup.thing.youtuber.dto;

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

    @Getter
    @Builder
    public static class ResVideoDto {
        private Long id;
        private String thumbnail;
        private String youtubeVideoId;
        private String title;
    }

}
