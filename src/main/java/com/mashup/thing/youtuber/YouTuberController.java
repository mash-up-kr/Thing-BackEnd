package com.mashup.thing.youtuber;
import com.mashup.thing.youtuber.dto.ResEndPageDto;
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

    @ApiOperation(value = "GET END(YouTuber) PAGE", notes = "GET END(YouTuber) PAGE")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "End Page 조회 성공"),
            @ApiResponse(code = 400, message = "YouTuberId - NULL or 존재하지 않는 id"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @GetMapping("/v1/youtubers/{youTuberId}")
    public ResponseEntity<ResEndPageDto> getEndPageYouTuber(@RequestHeader(value = "uid") String uid,
                                                            @PathVariable(value = "youTuberId") Long youTuberId) {
        return ResponseEntity.status(HttpStatus.OK).body(youTuberService.getEndPageYouTuber(youTuberId));
    }
}
