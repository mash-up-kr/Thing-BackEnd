package com.mashup.thing.recommendation;

import com.mashup.thing.recommendation.dto.*;
import com.mashup.thing.youtuber.domain.YouTuber;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RecommendationMapper {
    private static final String SEPARATOR = ",";
    private static final Integer VIDEO_LIMIT = 10;

    public ResRecommendationDto toNoneYouTuber() {
        return new ResRecommendationDto();
    }


    public ResHomeDto toSoaringYouTuber(Page<YouTuber> soaringYoutubers) {
        List<ResSoaringYouTuberDto> resSoaringYouTuberDtos = toResSoaringYouTuberDtos(soaringYoutubers);
        return new ResHomeDto(resSoaringYouTuberDtos);
    }

    public ResRecommendationDto toResRecommendationDto(List<YouTuber> youTubers) {

        List<ResYouTuberDto> resYouTuberDtos = toResYouTuberDto(youTubers);

        return new ResRecommendationDto(resYouTuberDtos);
    }

    public ResHomeDto toResHomeDto(List<YouTuber> youTubers, Page<YouTuber> soaringYoutubers) {

        List<ResYouTuberDto> resYouTuberDtos = toResYouTuberDto(youTubers);
        List<ResSoaringYouTuberDto> resSoaringYouTuberDtos = toResSoaringYouTuberDtos(soaringYoutubers);

        return new ResHomeDto(resYouTuberDtos, resSoaringYouTuberDtos);
    }

    private List<ResYouTuberDto> toResYouTuberDto(List<YouTuber> youTubers) {
        return youTubers.stream()
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
                                        .build())
                                .collect(Collectors.toList()))
                        .build()).collect(Collectors.toList());
    }

    private List<ResSoaringYouTuberDto> toResSoaringYouTuberDtos(Page<YouTuber> soaringYoutubers) {
        return soaringYoutubers.stream()
                .map(youTuber -> ResSoaringYouTuberDto.builder()
                        .id(youTuber.getId())
                        .name(youTuber.getName())
                        .soaring(youTuber.getSoaring())
                        .thumbnail(youTuber.getThumbnail())
                        .tag(Arrays.stream(
                                Optional.ofNullable(
                                        youTuber.getTag()).orElse(SEPARATOR).split(SEPARATOR))
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
    }

}
