package com.mashup.thing.review;

import com.mashup.thing.review.domain.Liked;
import com.mashup.thing.review.domain.Review;
import com.mashup.thing.review.dto.ReqWriteReviewDto;
import com.mashup.thing.user.domain.User;
import com.mashup.thing.youtuber.domain.YouTuber;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class ReviewMapper {
    public Review toReview(YouTuber youTuber, User user, ReqWriteReviewDto reqWriteReviewDto) {
        return new Review(LocalDateTime.now(),
                user.getNickName(),
                Optional.ofNullable(user.getProfileUrl()).orElse(""),
                youTuber.getThumbnail(),
                youTuber.getName(),
                reqWriteReviewDto.getText(),
                Liked.findByLiked(reqWriteReviewDto.getLiked()),
                youTuber.getId(),
                user.getId());
    }
}
