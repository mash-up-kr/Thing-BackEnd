package com.mashup.thing.youtuber.domain;

import com.mashup.thing.video.domain.Video;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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
    @Column(name = "description", length = 512)
    private String description;
    private LocalDateTime publishedAt;
    private String thumbnail;
    private Long subscriberCount;
    private Long viewCount;
    private Long videoCount;
    private Long commentCount;
    private Double soaring;
    private String bannerImgUrl;
    private String country;
    private Long likeCount;
    private Long noCount;
    private String commonTag;
    private String categoryTag;
    private Long categoryId;

    @OneToMany
    @JoinColumn(name = "youTuberId")
    private List<Video> videos;

    @ElementCollection
    @CollectionTable(name = "you_tuber_tag",
            joinColumns = @JoinColumn(name = "you_tuber_id"))
    private Set<Long> tagIds;


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