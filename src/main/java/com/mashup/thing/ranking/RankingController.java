package com.mashup.thing.ranking;

import com.mashup.thing.category.domain.CategoryType;
import com.mashup.thing.ranking.dto.ResRankingsDto;
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

    @ApiOperation(value = "GET RANKING LIST", notes = "GET RANKING LIST API - Page(Request PageIndex) / Page StartIndex - 0")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "RANKING 조회 성공"),
            @ApiResponse(code = 400, message = "page - NULL"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @GetMapping("/v1/rankings")
    public ResponseEntity<ResRankingsDto> getRankings(@RequestHeader(value = "uid") String uid,
                                                      @RequestParam(value = "page") Integer page,
                                                      @RequestParam(value = "filter") FilterType filter) {
        return ResponseEntity.status(HttpStatus.OK).body(rankingService.getRankings(page, filter, CategoryType.TOTAL.getPrimaryKey()));
    }

    @ApiOperation(value = "GET CATEGORY RANKING LIST", notes = "GET CATEGORY RANKING LIST API - Page(Request PageIndex) / Page StartIndex - 0" +
            "\n Category - 지식(1), 스포츠(2), 뷰티(3), 키즈(4), 토크(5), 일상(6), 게임(7), 푸드(8), 엔터테이먼트(9), 뮤직(10), 코믹(11)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "CATEGORY RANKING 조회 성공"),
            @ApiResponse(code = 400, message = "page - NULL"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @GetMapping("/v1/categories/{categoryId}/rankings")
    public ResponseEntity<ResRankingsDto> getRankings(@RequestHeader(value = "uid") String uid,
                                                      @RequestParam(value = "page") Integer page,
                                                      @RequestParam(value = "filter") FilterType filter,
                                                      @PathVariable Long categoryId) {
        return ResponseEntity.status(HttpStatus.OK).body(rankingService.getRankings(page, filter, categoryId));
    }
}
