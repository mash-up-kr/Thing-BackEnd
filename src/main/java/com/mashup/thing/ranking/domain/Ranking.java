package com.mashup.thing.ranking.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "ranking")
public class Ranking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long ranking;
    private LocalDateTime createAt;
    private Long viewCount;
    private Long subscriberCount;
    private String thumbnail;
    private String bannerImgUrl;
    private Long categoryId;
    private Long youTuberId;
    private String soaring;
}
