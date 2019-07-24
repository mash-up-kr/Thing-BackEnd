package com.mashup.thing.youtuber.service;

import com.mashup.thing.youtuber.dto.ReqRecommendationDto;
import com.mashup.thing.youtuber.dto.ResEndPageDto;
import com.mashup.thing.youtuber.dto.ResHomeDto;
import com.mashup.thing.youtuber.dto.ResRecommendationDto;
import org.springframework.stereotype.Service;

@Service
public class YouTuberService {
    private final EndPageYouTuberService endPageYouTuberService;
    private final RecommendationYouTuberService recommendationYouTuberService;

    public YouTuberService(EndPageYouTuberService endPageYouTuberService,
                           RecommendationYouTuberService recommendationYouTuberService) {
        this.endPageYouTuberService = endPageYouTuberService;
        this.recommendationYouTuberService = recommendationYouTuberService;
    }

    public ResRecommendationDto recommendationByTag(String uid, ReqRecommendationDto reqRecommendationDto) {
        return recommendationYouTuberService.searchByTag(uid, reqRecommendationDto);
    }

    public ResHomeDto recommendation(String uid) {
        return recommendationYouTuberService.searchByUser(uid);
    }

    public ResEndPageDto getEndPage(Long youTuberId, String uid) {
        return endPageYouTuberService.getEndPage(youTuberId, uid);
    }
}
