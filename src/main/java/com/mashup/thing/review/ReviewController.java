package com.mashup.thing.review;

import com.mashup.thing.exception.RequestNullFieldException;
import com.mashup.thing.review.dto.ReqWriteReviewDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @ApiOperation(value = "WRITE REVIEW", notes = "WRITE REVIEW API")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "리뷰 쓰기 성공"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @PostMapping("/v1/youtubers/{youTuberId}/reviews")
    public ResponseEntity<Void> getRankings(@RequestHeader(value = "uid") String uid,
                                            @PathVariable Long youTuberId,
                                            @RequestBody @Valid ReqWriteReviewDto reqWriteReviewDto,
                                            BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new RequestNullFieldException();
        }

        reviewService.writeReview(youTuberId, reqWriteReviewDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
