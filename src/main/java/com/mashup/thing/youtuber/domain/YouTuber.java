package com.mashup.thing.youtuber.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table
public class YouTuber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String channelId;
    @Lob
    @Column(name="description", length=512)
    private String description;
    private LocalDateTime publishedAt;
    private String thumbnail;
    private Long subscriberCount;
    private Long viewCount;
    private Long categoryId;
    private Long videoCount;
    private Long commentCount;
    private String bannerImgUrl;
    private String country;
    private Long likeCount;
    private Long noCount;

    public void increaseLikeReviewCount() {
        likeCount++;
    }

    public void increaseNoReviewCount() {
        noCount++;
    }

    public void decreaseLikeReviewCount() {
        likeCount--;
    }

    public void decreaseNoReviewCount() {
        noCount--;
    }
}