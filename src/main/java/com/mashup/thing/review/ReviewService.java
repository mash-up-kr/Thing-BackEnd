package com.mashup.thing.review;

import com.mashup.thing.review.dto.ReqWriteReviewDto;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final WriteReviewService writeReviewService;

    public ReviewService(WriteReviewService writeReviewService) {
        this.writeReviewService = writeReviewService;
    }

    public void writeReview(Long youTuberId, ReqWriteReviewDto reqWriteReviewDto) {
        writeReviewService.write(youTuberId, reqWriteReviewDto);
    }
}
