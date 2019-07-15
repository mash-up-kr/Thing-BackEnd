package com.mashup.thing.endpage.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ResEndPageVideoDto {

    private Long id;
    private String thumbnail;
    private String youtubeVideoId;
    private LocalDateTime publishedAt;
    private String title;

}
