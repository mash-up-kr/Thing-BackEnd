package com.mashup.thing.ranking;

import com.mashup.thing.category.domain.CategoryRepository;
import com.mashup.thing.exception.category.NotFoundCategoryException;
import com.mashup.thing.ranking.domain.CheckRankingRepository;
import com.mashup.thing.ranking.domain.RankingRepository;
import com.mashup.thing.ranking.service.CategoryRankingService;
import com.mashup.thing.ranking.service.RankingMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CategoryRankingServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private CategoryRepository categoryRepository;
    private RankingRepository rankingRepository;
    private CheckRankingRepository checkRankingRepository;
    private RankingMapper rankingMapper;

    private CategoryRankingService categoryRankingService;

    @Before
    public void mockUp() {
        categoryRepository = mock(CategoryRepository.class);
        checkRankingRepository = mock(CheckRankingRepository.class);
        rankingRepository = mock(RankingRepository.class);
        rankingMapper = mock(RankingMapper.class);

        categoryRankingService = new CategoryRankingService(categoryRepository, rankingRepository, checkRankingRepository, rankingMapper);
    }

    @Test
    public void testNotFoundCategory() {
        when(categoryRepository.existsById(any())).thenReturn(false);
        expectedException.expect(NotFoundCategoryException.class);
        categoryRankingService.getRankings(1, FilterType.SOARING, 1L);
    }
}