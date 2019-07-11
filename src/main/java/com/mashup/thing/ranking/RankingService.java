package com.mashup.thing.ranking;

import com.mashup.thing.category.domain.CategoryType;
import com.mashup.thing.ranking.domain.Ranking;
import com.mashup.thing.ranking.dto.ResRankingsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RankingService {

    private final RankingRepository rankingRepository;
    private final RankingMapper rankingMapper;

    private final static int LIST_SIZE = 50;

    public RankingService(RankingRepository rankingRepository, RankingMapper rankingMapper) {
        this.rankingRepository = rankingRepository;
        this.rankingMapper = rankingMapper;
    }

    public ResRankingsDto getRankings(int page, FilterType filter) {

        Page<Ranking> rankingPage = rankingRepository.findAllByCategoryId(
                CategoryType.TOTAL.getPrimaryKey(),
                PageRequest.of(page, LIST_SIZE, new Sort(Sort.Direction.ASC, filter.getFilterType())));


        if (isRankingListEmpty(rankingPage)) {
            return new ResRankingsDto();
        }

        return rankingMapper.toRankingsDto(rankingPage, filter);
    }

    private Boolean isRankingListEmpty(Page<Ranking> rankingPage) {
        return Optional.of(rankingPage.getContent().isEmpty()).get();
    }
}
