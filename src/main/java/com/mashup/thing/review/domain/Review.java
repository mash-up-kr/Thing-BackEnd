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
    @Lob
    @Column(name="text", length=512)
    private String text;
    @Enumerated(value = EnumType.STRING)
    private Liked liked;
    private Long youTuberId;
    private Long userId;

    public Boolean isOwner(Long userId) {
        return this.userId.equals(userId);
    }

}
