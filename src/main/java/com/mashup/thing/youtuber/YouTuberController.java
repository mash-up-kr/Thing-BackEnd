package com.mashup.thing.youtuber;

import com.mashup.thing.youtuber.dto.ResEndPageDto;
import com.mashup.thing.youtuber.dto.ReqRecommendationDto;
import com.mashup.thing.youtuber.dto.ResHomeDto;
import com.mashup.thing.youtuber.dto.ResRecommendationDto;
import com.mashup.thing.youtuber.service.YouTuberService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class YouTuberController {

    private final YouTuberService youTuberService;

    public YouTuberController(YouTuberService youTuberService) {
        this.youTuberService = youTuberService;
    }

    @ApiOperation(value = "RECOMMENDATION YOUTUBER", notes = "RECOMMENDATION YOUTUBER API")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "RECOMMENDATION YOUTUBER 성공"),
            @ApiResponse(code = 400, message = "잘 못 된 요청 - Not Found User"),
            @ApiResponse(code = 403, message = "인증 실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @GetMapping("/v1/youtubers/recommendations")
    public ResponseEntity<ResRecommendationDto> recommendationByTag(@RequestHeader(value = "uid") String uid,
                                                                    @ModelAttribute ReqRecommendationDto reqRecommendationDto) {
        return ResponseEntity.status(HttpStatus.OK).body(youTuberService.recommendationByTag(uid, reqRecommendationDto));
    }

    @ApiOperation(value = "RECOMMENDATION HOME", notes = "RECOMMENDATION HOME API")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "RECOMMENDATION HOME 성공"),
            @ApiResponse(code = 400, message = "잘 못 된 요청 - Not Found User"),
            @ApiResponse(code = 403, message = "인증 실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @GetMapping("/v1/youtubers/recommendations/home")
    public ResponseEntity<ResHomeDto> recommendation(@RequestHeader(value = "uid") String uid) {
        return ResponseEntity.status(HttpStatus.OK).body(youTuberService.recommendation(uid));
    }

    @ApiOperation(value = "GET END PAGE", notes = "GET END PAGE - YouTuber 상세보기")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "End Page 조회 성공"),
            @ApiResponse(code = 400, message = "YouTuberId - NULL or 존재하지 않는 id"),
            @ApiResponse(code = 403, message = "인증 실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @GetMapping("/v1/youtubers/{youTuberId}")
    public ResponseEntity<ResEndPageDto> getEndPageYouTuber(@RequestHeader(value = "uid") String uid,
                                                            @PathVariable(value = "youTuberId") Long youTuberId) {
        return ResponseEntity.status(HttpStatus.OK).body(youTuberService.getEndPage(youTuberId, uid));
    }
}
