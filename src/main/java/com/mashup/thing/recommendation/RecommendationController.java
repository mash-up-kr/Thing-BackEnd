package com.mashup.thing.recommendation;

import com.mashup.thing.recommendation.dto.ReqRecommendationDto;
import com.mashup.thing.recommendation.dto.ResRecommendationDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }


    @ApiOperation(value = "RECOMMENDATION YOUTUBER", notes = "RECOMMENDATION YOUTUBER API")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "RECOMMENDATION YOUTUBER 성공"),
            @ApiResponse(code = 403, message = "인증 실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @GetMapping("/v1/recommendations")
    public ResponseEntity<ResRecommendationDto> recommendationByTag(@RequestHeader(value = "uid") String uid,
                                                                    @ModelAttribute ReqRecommendationDto reqRecommendationDto) {
        ;
        return ResponseEntity.status(HttpStatus.OK).body(recommendationService.searchByTag(uid, reqRecommendationDto));
    }
}
