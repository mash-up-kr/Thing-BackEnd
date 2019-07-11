package com.mashup.thing.ranking;

import com.mashup.thing.ranking.dto.ResRankingsDto;
import org.springframework.stereotype.Service;

@Service
public class RankingService {

    private final CategoryRankingService categoryRankingService;

    public RankingService(CategoryRankingService categoryRankingService) {
        this.categoryRankingService = categoryRankingService;
    }

    public ResRankingsDto getRankings(Integer page, FilterType filter, Long categoryId) {
        return categoryRankingService.getRankings(page, filter, categoryId);

    }
}
