package com.mashup.thing.ranking;

import com.mashup.thing.ranking.domain.Ranking;
import com.mashup.thing.ranking.dto.ResRankingDto;
import com.mashup.thing.ranking.dto.ResRankingsDto;
import com.mashup.thing.util.MoneyCovert;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RankingMapper {
    public ResRankingsDto toRankingsDto(Page<Ranking> rankingPage, FilterType filter) {
        return toResRankingsDto(rankingPage, filter);
    }

    private ResRankingsDto toResRankingsDto(Page<Ranking> rankingPage, FilterType filter) {
        return ResRankingsDto.builder()
                .rankings(rankingPage.get().map(this::toRankingDto).collect(Collectors.toList()))
                .createAt(rankingPage.getContent().get(0).getCreateAt())
                .currentPage(rankingPage.getNumber())
                .nextPage(rankingPage.getPageable().next().getPageNumber())
                .totalPage(rankingPage.getTotalPages())
                .filterType(filter.getFilterType())
                .build();
    }


    private ResRankingDto toRankingDto(Ranking ranking) {
        return ResRankingDto.builder()
                .id(ranking.getYouTuberId())
                .name(ranking.getName())
                .ranking(ranking.getRanking())
                .thumbnail(ranking.getThumbnail())
                .bannerImgUrl(ranking.getBannerImgUrl())
                .viewCount(MoneyCovert.toKoreaUnit(ranking.getViewCount()))
                .subscriberCount(MoneyCovert.toKoreaUnit(ranking.getSubscriberCount()))
                .soaring(ranking.getSoaring().toString())
                .build();
    }
}



