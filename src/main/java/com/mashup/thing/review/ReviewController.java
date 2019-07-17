package com.mashup.thing.review;

import com.mashup.thing.exception.RequestNullFieldException;
import com.mashup.thing.review.dto.ReqDeleteReviewDto;
import com.mashup.thing.review.dto.ReqWriteReviewDto;
import com.mashup.thing.review.service.ReviewService;
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
            @ApiResponse(code = 204, message = "리뷰 쓰기 성공"),
            @ApiResponse(code = 400, message = "Not Found User or YouTuber, Null Field"),
            @ApiResponse(code = 403, message = "Fail Authentication"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @PostMapping("/v1/reviews")
    public ResponseEntity<Void> writeReview(@RequestHeader(value = "uid") String uid,
                                            @RequestBody @Valid ReqWriteReviewDto reqWriteReviewDto,
                                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new RequestNullFieldException();
        }

        reviewService.writeReview(reqWriteReviewDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "DELETE REVIEW", notes = "DELETE REVIEW API")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "리뷰 삭제 성공"),
            @ApiResponse(code = 400, message = "Not Found User or YouTuber, Null Field"),
            @ApiResponse(code = 403, message = "Fail Authentication"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @DeleteMapping("/v1/reviews/{reviewId}")
    public ResponseEntity<Void> deleteReview(@RequestHeader(value = "uid") String uid,
                                             @ModelAttribute @Valid ReqDeleteReviewDto reqDeleteReviewDto,
                                             @PathVariable Long reviewId,
                                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new RequestNullFieldException();
        }

        reviewService.deleteReview(reviewId, reqDeleteReviewDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
