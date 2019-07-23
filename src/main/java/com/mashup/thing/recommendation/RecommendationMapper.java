package com.mashup.thing.recommendation;

import com.mashup.thing.recommendation.dto.ResRecommendationDto;
import com.mashup.thing.recommendation.dto.ResVideoDto;
import com.mashup.thing.recommendation.dto.ResYouTuberDto;
import com.mashup.thing.youtuber.domain.YouTuber;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RecommendationMapper {
    private static final String SEPARATOR = ",";
    private static final Integer VIDEO_LIMIT = 10;

    public ResRecommendationDto toNoneYouTuber() {

        return new ResRecommendationDto();
    }

    public ResRecommendationDto toResRecommendationDto(List<YouTuber> youTubers) {

        List<ResYouTuberDto> resYouTuberDtos = youTubers.stream()
                .map(youTuber -> ResYouTuberDto.builder()
                        .id(youTuber.getId())
                        .name(youTuber.getName())
                        .thumbnail(youTuber.getThumbnail())
                        .tag(Arrays.stream(youTuber.getTag().split(SEPARATOR)).collect(Collectors.toList()))
                        .videos(youTuber.getVideos().stream().limit(VIDEO_LIMIT).map(
                                video -> ResVideoDto.builder()
                                        .id(video.getId())
                                        .thumbnail(video.getThumbnail())
                                        .title(video.getTitle())
                                        .youtubeVideoId(video.getYoutubeVideoId())
                                        .build()
                        ).collect(Collectors.toList()))
                        .build()).collect(Collectors.toList());

        return new ResRecommendationDto(resYouTuberDtos);
    }
}
