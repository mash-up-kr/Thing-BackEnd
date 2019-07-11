package com.mashup.thing.ranking.dto;

import com.mashup.thing.ranking.domain.Ranking;
import com.mashup.thing.util.MoneyCovert;
import lombok.Getter;

@Getter
public class ResRankingDto {

    private Long id;
    private String name;
    private Long ranking;
    private String viewCount;
    private String subscriberCount;
    private String thumbnail;
    private String bannerImgUrl;

    private ResRankingDto(Ranking ranking) {
        this.id = ranking.getId();
        this.name = ranking.getName();
        this.ranking = ranking.getRanking();
        this.thumbnail = ranking.getThumbnail();
        this.bannerImgUrl = ranking.getBannerImgUrl();
        this.viewCount = MoneyCovert.toKoreaUnit(ranking.getViewCount());
        this.subscriberCount = MoneyCovert.toKoreaUnit(ranking.getSubscriberCount());
    }

    public static ResRankingDto from(Ranking ranking) {
        return new ResRankingDto(ranking);
    }
}
