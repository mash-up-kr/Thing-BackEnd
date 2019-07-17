package com.mashup.thing.review.service;

import com.mashup.thing.review.dto.ReqDeleteReviewDto;
import com.mashup.thing.review.dto.ReqWriteReviewDto;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final WriteReviewService writeReviewService;
    private final DeleteReviewService deleteReviewService;

    public ReviewService(WriteReviewService writeReviewService, DeleteReviewService deleteReviewService) {
        this.writeReviewService = writeReviewService;
        this.deleteReviewService = deleteReviewService;
    }

    public void writeReview(ReqWriteReviewDto reqWriteReviewDto) {
        writeReviewService.write(reqWriteReviewDto);
    }

    public void deleteReview(Long reviewId, ReqDeleteReviewDto reqDeleteReviewDto) {
        deleteReviewService.delete(reviewId, reqDeleteReviewDto);
    }
}
