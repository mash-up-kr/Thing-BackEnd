package com.mashup.thing.ranking;

import com.mashup.thing.ranking.domain.Ranking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RankingRepository extends JpaRepository<Ranking, Long> {
    Page<Ranking> findAllByCategoryId(Long categoryId, Pageable pageable);
}
