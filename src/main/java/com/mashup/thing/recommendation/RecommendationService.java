package com.mashup.thing.recommendation;

import com.mashup.thing.recommendation.dto.ReqRecommendationDto;
import com.mashup.thing.recommendation.dto.ResRecommendationDto;
import org.springframework.stereotype.Service;

@Service
public class RecommendationService {
    private final RecommendationByTagService recommendationByTagService;


    public RecommendationService(
            RecommendationByTagService recommendationByTagService) {
        this.recommendationByTagService = recommendationByTagService;
    }

    public ResRecommendationDto searchByTag(String uid, ReqRecommendationDto reqRecommendationDto) {
        return recommendationByTagService.searchByTag(uid, reqRecommendationDto);
    }
}
