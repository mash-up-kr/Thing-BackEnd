package com.mashup.thing.ranking;

import com.mashup.thing.ranking.domain.Ranking;
import com.mashup.thing.ranking.dto.ResRankingDto;
import com.mashup.thing.ranking.dto.ResRankingsDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RankingMapper {
    public ResRankingsDto toRankingsDto(Page<Ranking> rankingPage, FilterType filterType) {
        return new ResRankingsDto(
                rankingPage.get().map(ResRankingDto::from).collect(Collectors.toList()),
                rankingPage.getNumber(),
                rankingPage.getPageable().next().getPageNumber(),
                rankingPage.getTotalPages(),
                rankingPage.getContent().get(0).getCreateAt(),
                filterType.getFilterType());
    }
}
