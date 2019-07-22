package com.mashup.thing.ranking;

import com.mashup.thing.ranking.dto.ResRankingsDto;
import com.mashup.thing.ranking.service.RankingService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RankingController {

    private final RankingService rankingService;

    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @ApiOperation(value = "GET CATEGORY RANKING LIST", notes = "GET CATEGORY RANKING LIST API - Page(Request PageIndex) / Page StartIndex - 0" +
            "\n Category - 전체(1), 게임(2), 뷰티(3), 스포츠(4), 엔터테인먼트(5), 음악(6), 일상(7), 지식(8), 키즈(9), 펫(10), 푸드(11)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "CATEGORY RANKING 조회 성공"),
            @ApiResponse(code = 400, message = "존재 하지 않는 카테고리"),
            @ApiResponse(code = 403, message = "인증 실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @GetMapping("/v1/categories/{categoryId}/rankings")
    public ResponseEntity<ResRankingsDto> getCategoryRankings(@RequestHeader(value = "uid") String uid,
                                                      @RequestParam(value = "page") Integer page,
                                                      @RequestParam(value = "filter") FilterType filter,
                                                      @PathVariable Long categoryId) {
        return ResponseEntity.status(HttpStatus.OK).body(rankingService.getRankings(page, filter, categoryId));
    }
}
