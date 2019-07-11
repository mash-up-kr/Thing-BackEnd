package com.mashup.thing.ranking;

import com.mashup.thing.category.CategoryRepository;
import com.mashup.thing.exception.BaseException;
import com.mashup.thing.ranking.domain.Ranking;
import com.mashup.thing.ranking.dto.ResRankingsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryRankingService {

    private final CategoryRepository categoryRepository;
    private final RankingRepository rankingRepository;
    private final RankingMapper rankingMapper;

    private final static int LIST_SIZE = 50;

    public CategoryRankingService(CategoryRepository categoryRepository,
                                  RankingRepository rankingRepository,
                                  RankingMapper rankingMapper) {
        this.categoryRepository = categoryRepository;
        this.rankingRepository = rankingRepository;
        this.rankingMapper = rankingMapper;
    }

    public ResRankingsDto getRankings(Integer page, FilterType filter, Long categoryId) {

        if(isCategory(categoryId)) {
            throw new BaseException(HttpStatus.BAD_REQUEST);
        }

        Sort sort = checkFilterType(filter);

        Page<Ranking> rankingPage = rankingRepository.findAllByCategoryId(
                categoryId, PageRequest.of(page, LIST_SIZE, sort));

        if (isRankingListEmpty(rankingPage)) {
            return new ResRankingsDto();
        }

        return rankingMapper.toRankingsDto(rankingPage, filter);
    }

    private Boolean isCategory(Long categoryId) {
        return !(categoryRepository.existsById(categoryId));
    }

    private Sort checkFilterType(FilterType filter) {
        if (filter.equals(FilterType.TOTAL)) {
            return new Sort(Sort.Direction.ASC, filter.getFilterType());
        }
        return new Sort(Sort.Direction.DESC, filter.getFilterType());
    }

    private Boolean isRankingListEmpty(Page<Ranking> rankingPage) {
        return Optional.of(rankingPage.getContent().isEmpty()).get();
    }
}
