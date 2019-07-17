package com.mashup.thing.endpage;

import com.mashup.thing.endpage.dto.ResEndPageDto;
import com.mashup.thing.endpage.dto.ResEndPageReviewDto;
import com.mashup.thing.endpage.dto.ResEndPageVideoDto;
import com.mashup.thing.review.domain.Review;
import com.mashup.thing.video.domain.Video;
import com.mashup.thing.youtuber.domain.YouTuber;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EndPageMapper {
    public ResEndPageDto toEndPage(YouTuber youTuber, List<Video> videos,
                                   List<Review> likeReview, List<Review> noReview,
                                   Long userId) {

        List<ResEndPageVideoDto> resEndPageVideos = this.toResEndPageVideoDto(videos);
        List<ResEndPageReviewDto> resEndPageLikeReviews = this.toResEndReviewDto(likeReview, userId);
        List<ResEndPageReviewDto> resEndPageNoReviews = this.toResEndReviewDto(noReview, userId);

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

    private List<ResEndPageVideoDto> toResEndPageVideoDto(List<Video> videos) {
        if (videos.isEmpty()) {
            return new ArrayList<>();
        }
        return videos.stream()
                .map(video -> ResEndPageVideoDto.builder()
                        .id(video.getId())
                        .publishedAt(video.getPublishedAt())
                        .thumbnail(video.getThumbnail())
                        .title(video.getTitle())
                        .youtubeVideoId(video.getYoutubeVideoId())
                        .build())
                .collect(Collectors.toList());
    }

    private List<ResEndPageReviewDto> toResEndReviewDto(List<Review> likeReview, Long userId) {
        if (likeReview.isEmpty()) {
            return new ArrayList<>();
        }
        return likeReview.stream()
                .map(review -> ResEndPageReviewDto.builder()
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
