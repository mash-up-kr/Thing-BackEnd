package com.mashup.thing.ranking;

import com.mashup.thing.ranking.domain.CheckRanking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckRankingRepository extends JpaRepository<CheckRanking, Long> {

    CheckRanking findTopByCategoryIdAndRankingTypeOrderByCreateAtDesc(Long CategoryId, String rankingType);
}
