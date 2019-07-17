package com.mashup.thing.endpage;

import com.mashup.thing.endpage.dto.ResEndPageDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EndPageController {

    private final EndPageService endPageService;

    public EndPageController(EndPageService endPageService) {
        this.endPageService = endPageService;
    }

    @ApiOperation(value = "GET END PAGE", notes = "GET END PAGE")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "End Page 조회 성공"),
            @ApiResponse(code = 400, message = "YouTuberId - NULL or 존재하지 않는 id"),
            @ApiResponse(code = 403, message = "인증 실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @GetMapping("/v1/endpages/{youTuberId}")
    public ResponseEntity<ResEndPageDto> getEndPageYouTuber(@RequestHeader(value = "uid") String uid,
                                                            @PathVariable(value = "youTuberId") Long youTuberId,
                                                            @RequestParam Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(endPageService.getEndPage(youTuberId, userId));
    }
}
