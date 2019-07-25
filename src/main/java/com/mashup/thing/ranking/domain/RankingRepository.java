package com.mashup.thing.ranking.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface RankingRepository extends JpaRepository<Ranking, Long> {
    Page<Ranking> findAllByCategoryIdAndCreateAtAndRankingType(Long categoryId, LocalDateTime createAt,
                                                               String rankingType, Pageable pageable);
}
