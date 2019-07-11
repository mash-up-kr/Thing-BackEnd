package com.mashup.thing.youtuber;

import com.mashup.thing.review.domain.Review;
import com.mashup.thing.video.domain.Video;
import com.mashup.thing.youtuber.domain.YouTuber;
import com.mashup.thing.youtuber.dto.ResEndPageDto;
import com.mashup.thing.youtuber.dto.ResEndPageReviewDto;
import com.mashup.thing.youtuber.dto.ResEndPageVideoDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class YouTuberMapper {
    public ResEndPageDto toEndPage(YouTuber youTuber, List<Video> videos, List<Review> likeReview, List<Review> noReview) {

        List<ResEndPageVideoDto> resEndPageVideos = this.toResEndPageVideoDto(videos);
        List<ResEndPageReviewDto> resEndPageLikeReviews = this.toResEndReviewDto(likeReview);
        List<ResEndPageReviewDto> resEndPageNoReviews = this.toResEndReviewDto(noReview);

        return new ResEndPageDto(youTuber.getId(), youTuber.getName(), youTuber.getBannerImgUrl(),
                youTuber.getChannelId(), youTuber.getDescription(), youTuber.getPublishedAt(),
                youTuber.getSubscriberCount(), youTuber.getThumbnail(), youTuber.getViewCount(),
                resEndPageVideos, resEndPageLikeReviews, resEndPageNoReviews);
    }

    private List<ResEndPageVideoDto> toResEndPageVideoDto(List<Video> videos) {
        if (videos.isEmpty()) {
            return new ArrayList<>();
        }
        return videos.stream().map(ResEndPageVideoDto::from).collect(Collectors.toList());
    }

    private List<ResEndPageReviewDto> toResEndReviewDto(List<Review> likeReview) {
        if (likeReview.isEmpty()) {
            return new ArrayList<>();
        }
        return likeReview.stream().map(ResEndPageReviewDto::from).collect(Collectors.toList());
    }


}
