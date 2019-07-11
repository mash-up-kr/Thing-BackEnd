package com.mashup.thing.youtuber.dto;

import com.mashup.thing.review.domain.Review;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResEndPageReviewDto {

    private Long id;
    private LocalDateTime createAt;
    private String nickName;
    private String profileUrl;
    private String text;
    private String liked;

    private ResEndPageReviewDto(Review review) {
        this.id = review.getId();
        this.createAt = review.getCreateAt();
        this.nickName = review.getNickName();
        this.profileUrl = review.getProfileUrl();
        this.text = review.getText();
        this.liked = review.getLiked().getLiked();
    }

    public static ResEndPageReviewDto from(Review review) {
        return new ResEndPageReviewDto(review);
    }
}
