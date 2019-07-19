package com.mashup.thing.ranking;

import com.mashup.thing.category.CategoryRepository;
import com.mashup.thing.exception.category.NotFoundCategoryException;
import com.mashup.thing.ranking.domain.CheckRanking;
import com.mashup.thing.ranking.domain.Ranking;
import com.mashup.thing.ranking.dto.ResRankingsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CategoryRankingService {

    private final CategoryRepository categoryRepository;
    private final RankingRepository rankingRepository;
    private final CheckRankingRepository checkRankingRepository;
    private final RankingMapper rankingMapper;

    private final static int LIST_SIZE = 50;

    public CategoryRankingService(CategoryRepository categoryRepository,
                                  RankingRepository rankingRepository,
                                  CheckRankingRepository checkRankingRepository, RankingMapper rankingMapper) {
        this.categoryRepository = categoryRepository;
        this.rankingRepository = rankingRepository;
        this.checkRankingRepository = checkRankingRepository;
        this.rankingMapper = rankingMapper;
    }

    public ResRankingsDto getRankings(Integer page, FilterType filter, Long categoryId) {
        if (isCategory(categoryId)) {
            throw new NotFoundCategoryException();
        }

        CheckRanking check =
                checkRankingRepository.findTopByCategoryIdAndRankingTypeOrderByCreateAtDesc(categoryId, filter.getFilterType());

        Page<Ranking> rankingPage = rankingRepository.findAllByCategoryIdAndCreateAtAndRankingType(
                categoryId, check.getCreateAt(), check.getRankingType(), PageRequest.of(page, LIST_SIZE));

        if (isRankingListEmpty(rankingPage)) {
            return ResRankingsDto.builder().rankings(new ArrayList<>()).build();
        }

        return rankingMapper.toRankingsDto(rankingPage, filter);
    }

    private Boolean isCategory(Long categoryId) {
        return !(categoryRepository.existsById(categoryId));
    }


    private Boolean isRankingListEmpty(Page<Ranking> rankingPage) {
        return Optional.of(rankingPage.getContent().isEmpty()).get();
    }
}
