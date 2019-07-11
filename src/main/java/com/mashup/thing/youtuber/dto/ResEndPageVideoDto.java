package com.mashup.thing.youtuber.dto;

import com.mashup.thing.video.domain.Video;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResEndPageVideoDto {

    private Long id;
    private String thumbnail;
    private String youtubeVideoId;
    private LocalDateTime publishedAt;
    private String title;

    private ResEndPageVideoDto(Video video) {
        this.id = video.getId();
        this.thumbnail = video.getThumbnail();
        this.youtubeVideoId = video.getYoutubeVideoId();
        this.title = video.getTitle();
        this.thumbnail = video.getThumbnail();
        this.publishedAt = video.getPublishedAt();
    }

    public static ResEndPageVideoDto from(Video video) {
        return new ResEndPageVideoDto(video);
    }
}
