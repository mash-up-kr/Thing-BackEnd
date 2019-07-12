package com.mashup.thing.endpage;

import com.mashup.thing.endpage.dto.ResEndPageDto;
import com.mashup.thing.endpage.dto.ResEndPageReviewDto;
import com.mashup.thing.endpage.dto.ResEndPageVideoDto;
import com.mashup.thing.review.domain.Review;
import com.mashup.thing.user.domain.User;
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
                                   User user) {

        List<ResEndPageVideoDto> resEndPageVideos = this.toResEndPageVideoDto(videos);
        List<ResEndPageReviewDto> resEndPageLikeReviews = this.toResEndReviewDto(likeReview, user.getId());
        List<ResEndPageReviewDto> resEndPageNoReviews = this.toResEndReviewDto(noReview, user.getId());

        return new ResEndPageDto(youTuber.getId(), youTuber.getName(),
                youTuber.getBannerImgUrl(), youTuber.getChannelId(),
                youTuber.getDescription(), youTuber.getPublishedAt(),
                youTuber.getSubscriberCount(), youTuber.getThumbnail(),
                youTuber.getViewCount(), youTuber.getLikeCount(), youTuber.getNoCount(),
                resEndPageVideos, resEndPageLikeReviews, resEndPageNoReviews);
    }

    private List<ResEndPageVideoDto> toResEndPageVideoDto(List<Video> videos) {
        if (videos.isEmpty()) {
            return new ArrayList<>();
        }
        return videos.stream().map(ResEndPageVideoDto::from).collect(Collectors.toList());
    }

    private List<ResEndPageReviewDto> toResEndReviewDto(List<Review> likeReview, Long userId) {
        if (likeReview.isEmpty()) {
            return new ArrayList<>();
        }
        return likeReview.stream()
                .map(review -> ResEndPageReviewDto.from(review, userId))
                .collect(Collectors.toList());
    }


}
