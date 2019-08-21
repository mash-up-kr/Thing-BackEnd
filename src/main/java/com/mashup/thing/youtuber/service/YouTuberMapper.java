package com.mashup.thing.youtuber.service;

import com.mashup.thing.category.domain.CategoryType;
import com.mashup.thing.youtuber.dto.ResEndPageDto;
import com.mashup.thing.review.domain.Review;
import com.mashup.thing.video.domain.Video;
import com.mashup.thing.youtuber.domain.YouTuber;
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

        resEndPageLikeReviews.addAll(resEndPageLikeReviews);

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
                .category(CategoryType.findByCategory(youTuber.getCategoryId()).getCategoryType())
                .commonTags(Arrays.stream(
                        Optional.ofNullable(
                                youTuber.getCommonTag()).orElse(SEPARATOR).split(SEPARATOR)).collect(Collectors.toList()))
                .categoryTags(Arrays.stream(
                        Optional.ofNullable(
                                youTuber.getCategoryTag()).orElse(SEPARATOR).split(SEPARATOR)).collect(Collectors.toList()))
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
                        .category(CategoryType.findByCategory(youTuber.getCategoryId()).getCategoryType())
                        .thumbnail(youTuber.getThumbnail())
                        .tag(mergeTag(youTuber.getCategoryTag(), youTuber.getCommonTag()))
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
                        .category(CategoryType.findByCategory(youTuber.getCategoryId()).getCategoryType())
                        .soaring(youTuber.getSoaring())
                        .thumbnail(youTuber.getThumbnail())
                        .tag(mergeTag(youTuber.getCategoryTag(), youTuber.getCommonTag()))
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

    private List<String> mergeTag(String categoryTag, String commonTag) {
        categoryTag = Optional.ofNullable(categoryTag).orElse(SEPARATOR);
        commonTag = Optional.ofNullable(commonTag).orElse(SEPARATOR);
        List<String> mergeTags =  Arrays.stream(commonTag.split(SEPARATOR)).collect(Collectors.toList());
        mergeTags.addAll(Arrays.stream(categoryTag.split(SEPARATOR)).collect(Collectors.toList()));

        return mergeTags;
    }

}
