package com.mashup.thing.ranking;

import com.mashup.thing.ranking.dto.ResRankingsDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RankingController {

    private final RankingService rankingService;

    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @ApiOperation(value = "RANKING GET LIST", notes = "GET RANKING LIST API - Page(Request PageIndex) / Page StartIndex - 0")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "RANKING 조회 성공"),
            @ApiResponse(code = 400, message = "page - NULL"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @GetMapping("/v1/rankings")
    public ResponseEntity<ResRankingsDto> getRankings(@RequestHeader(value = "uid") String uid,
                                                      @RequestParam(value = "page") Integer page,
                                                      @RequestParam(value = "filter") FilterType filter) {
        return ResponseEntity.status(HttpStatus.OK).body(rankingService.getRankings(page, filter));
    }
}
