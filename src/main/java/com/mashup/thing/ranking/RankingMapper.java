package com.mashup.thing.ranking;

import com.mashup.thing.ranking.domain.Ranking;
import com.mashup.thing.ranking.dto.ResRankingDto;
import com.mashup.thing.ranking.dto.ResRankingsDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RankingMapper {
    public ResRankingsDto toRankingsDto(Page<Ranking> rankingPage, FilterType filter) {
        if (filter.equals(FilterType.TOTAL)) {
            return new ResRankingsDto(
                    rankingPage.get().map(ResRankingDto::from).collect(Collectors.toList()),
                    rankingPage.getNumber(),
                    rankingPage.getPageable().next().getPageNumber(),
                    rankingPage.getTotalPages(),
                    rankingPage.getContent().get(0).getCreateAt(),
                    filter.getFilterType());
        }

        return soaringRanking(rankingPage, filter);
    }

    //ToDo 급상승 임시 로직 추후 배치를 통해서 처리
    private ResRankingsDto soaringRanking(Page<Ranking> rankingPage, FilterType filter) {
        int size = rankingPage.getContent().size();
        for (int i = 1; i <= size; i++) {

            int num = (rankingPage.getPageable().getPageNumber() * 50) + i;

            rankingPage.getContent().get(i - 1).setRanking((long) num);
        }

        return new ResRankingsDto(
                rankingPage.get().map(ResRankingDto::from).collect(Collectors.toList()),
                rankingPage.getNumber(),
                rankingPage.getPageable().next().getPageNumber(),
                rankingPage.getTotalPages(),
                rankingPage.getContent().get(0).getCreateAt(),
                filter.getFilterType());
    }
}



