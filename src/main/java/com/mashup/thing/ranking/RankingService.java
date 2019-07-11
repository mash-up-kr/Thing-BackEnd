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

        Sort sort = checkFilterType(filter);

        Page<Ranking> rankingPage = rankingRepository.findAllByCategoryId(
                CategoryType.TOTAL.getPrimaryKey(),
                PageRequest.of(page, LIST_SIZE, sort));

        if (isRankingListEmpty(rankingPage)) {
            return new ResRankingsDto();
        }

        return rankingMapper.toRankingsDto(rankingPage, filter);
    }

    private Sort checkFilterType(FilterType filter) {
        if (filter.getFilterType().equals(FilterType.TOTAL)) {
            return new Sort(Sort.Direction.ASC, filter.getFilterType());
        }
        return new Sort(Sort.Direction.DESC, filter.getFilterType());
    }

    private Boolean isRankingListEmpty(Page<Ranking> rankingPage) {
        return Optional.of(rankingPage.getContent().isEmpty()).get();
    }
}
