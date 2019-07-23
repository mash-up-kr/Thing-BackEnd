package com.mashup.thing.recommendation;

import com.mashup.thing.recommendation.dto.ReqRecommendationDto;
import com.mashup.thing.recommendation.dto.ResHomeDto;
import com.mashup.thing.recommendation.dto.ResRecommendationDto;
import org.springframework.stereotype.Service;

@Service
public class RecommendationService {
    private final SearchRecommendationService searchRecommendationService;


    public RecommendationService(
            SearchRecommendationService searchRecommendationService) {
        this.searchRecommendationService = searchRecommendationService;
    }

    public ResRecommendationDto searchByTag(String uid, ReqRecommendationDto reqRecommendationDto) {
        return searchRecommendationService.searchByTag(uid, reqRecommendationDto);
    }

    public ResHomeDto searchByUser(String uid) {
        return searchRecommendationService.searchByUser(uid);
    }
}
