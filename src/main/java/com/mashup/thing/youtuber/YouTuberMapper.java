package com.mashup.thing.youtuber;

import com.mashup.thing.review.domain.Review;
import com.mashup.thing.video.domain.Video;
import com.mashup.thing.youtuber.domain.YouTuber;
import com.mashup.thing.youtuber.dto.ResEndPageDto;
import com.mashup.thing.youtuber.dto.ResHomeDto;
import com.mashup.thing.youtuber.dto.ResRecommendationDto;
import com.mashup.thing.youtuber.dto.ResYouTuberDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class YouTuberMapper {

    private static final String SEPARATOR = ",";
    private static final Integer VIDEO_LIMIT = 10;

    public ResEndPageDto toEndPage(YouTuber youTuber, List<Video> videos,
                                   List<Review> likeReview, List<Review> noReview,
                                   Long userId) {

        List<ResEndPageDto.ResEndPageVideoDto> resEndPageVideos = this.toResEndPageVideoDto(videos);
        List<ResEndPageDto.ResEndPageReviewDto> resEndPageLikeReviews = this.toResEndReviewDto(likeReview, userId);
        List<ResEndPageDto.ResEndPageReviewDto> resEndPageNoReviews = this.toResEndReviewDto(noReview, userId);

        return ResEndPageDto.builder()
                .id(youTuber.getId())
                .name(youTuber.getName())
                .bannerImgUrl(youTuber.getBannerImgUrl())
                .channelId(youTuber.getChannelId())
                .description(youTuber.getDescription())
                .publishedAt(youTuber.getPublishedAt())
                .subscriberCount(youTuber.getSubscriberCount())
                .thumbnail(youTuber.getThumbnail())
                .viewCount(youTuber.getViewCount())
                .likeReviewCount(youTuber.getLikeCount())
                .noReviewCount(youTuber.getNoCount())
                .videos(resEndPageVideos)
                .likeReviews(resEndPageLikeReviews)
                .noReviews(resEndPageNoReviews)
                .build();
    }

    public ResRecommendationDto toNoneYouTuber() {
        return new ResRecommendationDto();
    }

    public ResHomeDto toSoaringYouTuber(Page<YouTuber> soaringYoutubers) {
        List<ResHomeDto.ResSoaringYouTuberDto> resSoaringYouTuberDtos = toResSoaringYouTuberDtos(soaringYoutubers);
        return new ResHomeDto(resSoaringYouTuberDtos);
    }

    public ResRecommendationDto toResRecommendationDto(List<YouTuber> youTubers) {

        List<ResYouTuberDto> resYouTuberDtos = toResYouTuberDto(youTubers);

        return new ResRecommendationDto(resYouTuberDtos);
    }

    public ResHomeDto toResHomeDto(List<YouTuber> youTubers, Page<YouTuber> soaringYoutubers) {

        List<ResYouTuberDto> resYouTuberDtos = toResYouTuberDto(youTubers);
        List<ResHomeDto.ResSoaringYouTuberDto> resSoaringYouTuberDtos = toResSoaringYouTuberDtos(soaringYoutubers);

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
                                video -> ResYouTuberDto.ResVideoDto.builder()
                                        .id(video.getId())
                                        .thumbnail(video.getThumbnail())
                                        .title(video.getTitle())
                                        .youtubeVideoId(video.getYoutubeVideoId())
                                        .build())
                                .collect(Collectors.toList()))
                        .build()).collect(Collectors.toList());
    }

    private List<ResHomeDto.ResSoaringYouTuberDto> toResSoaringYouTuberDtos(Page<YouTuber> soaringYoutubers) {
        return soaringYoutubers.stream()
                .map(youTuber -> ResHomeDto.ResSoaringYouTuberDto.builder()
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

    private List<ResEndPageDto.ResEndPageVideoDto> toResEndPageVideoDto(List<Video> videos) {
        if (videos.isEmpty()) {
            return new ArrayList<>();
        }
        return videos.stream()
                .map(video -> ResEndPageDto.ResEndPageVideoDto.builder()
                        .id(video.getId())
                        .publishedAt(video.getPublishedAt())
                        .thumbnail(video.getThumbnail())
                        .title(video.getTitle())
                        .youtubeVideoId(video.getYoutubeVideoId())
                        .build())
                .collect(Collectors.toList());
    }

    private List<ResEndPageDto.ResEndPageReviewDto> toResEndReviewDto(List<Review> likeReview, Long userId) {
        if (likeReview.isEmpty()) {
            return new ArrayList<>();
        }
        return likeReview.stream()
                .map(review -> ResEndPageDto.ResEndPageReviewDto.builder()
                        .id(review.getId())
                        .createAt(review.getCreateAt())
                        .liked(review.getLiked().toString())
                        .nickName(review.getNickName())
                        .text(review.getText())
                        .profileUrl(review.getProfileUrl())
                        .owner(review.isOwner(userId))
                        .build())
                .collect(Collectors.toList());
    }


}
