package com.mashup.thing.endpage.dto;

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
    private Boolean owner;

    private ResEndPageReviewDto(Review review, Long userId) {
        this.id = review.getId();
        this.createAt = review.getCreateAt();
        this.nickName = review.getNickName();
        this.profileUrl = review.getProfileUrl();
        this.text = review.getText();
        this.liked = review.getLiked().getLiked();
        this.owner = review.isOwner(userId);
    }

    public static ResEndPageReviewDto from(Review review, Long userId) {
        return new ResEndPageReviewDto(review, userId);
    }
}
