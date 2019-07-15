package com.mashup.thing.review.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createAt;
    private String nickName;
    private String profileUrl;
    private String youTuberThumbnail;
    private String youTuberName;
    @Lob
    @Column(name = "text", length = 512)
    private String text;
    @Enumerated(value = EnumType.STRING)
    private Liked liked;
    private Long youTuberId;
    private Long userId;

    public Review(LocalDateTime crateAt, String nickName, String profileUrl,
                  String thumbnail, String name, String text,
                  Liked liked, Long youTuberId, Long userId) {
        this.createAt = crateAt;
        this.nickName = nickName;
        this.profileUrl = profileUrl;
        this.youTuberThumbnail = thumbnail;
        this.youTuberName = name;
        this.text = text;
        this.liked = liked;
        this.youTuberId = youTuberId;
        this.userId = userId;
    }

    public Boolean isOwner(Long userId) {
        return this.userId.equals(userId);
    }

}
