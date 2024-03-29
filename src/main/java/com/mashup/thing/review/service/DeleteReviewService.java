package com.mashup.thing.review.service;

import com.mashup.thing.exception.review.NotFoundReviewException;
import com.mashup.thing.exception.youtuber.NotFoundYouTuBerException;
import com.mashup.thing.review.domain.ReviewRepository;
import com.mashup.thing.review.domain.Review;
import com.mashup.thing.review.dto.ReqDeleteReviewDto;
import com.mashup.thing.youtuber.domain.YouTuberRepository;
import com.mashup.thing.youtuber.domain.YouTuber;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteReviewService {

    private final YouTuberRepository youTuberRepository;
    private final ReviewRepository reviewRepository;

    public DeleteReviewService(YouTuberRepository youTuberRepository,
                               ReviewRepository reviewRepository) {
        this.youTuberRepository = youTuberRepository;
        this.reviewRepository = reviewRepository;
    }

    @Transactional
    public void delete(Long reviewId, ReqDeleteReviewDto reqDeleteReviewDto) {

        Review review = reviewRepository.findByIdAndUserIdAndYouTuberId(reviewId, reqDeleteReviewDto.getUserId(),
                reqDeleteReviewDto.getYouTuberId()).orElseThrow(NotFoundReviewException::new);

        reviewRepository.delete(review);

        YouTuber youTuber = youTuberRepository.findById(review.getYouTuberId())
                .orElseThrow(NotFoundYouTuBerException::new);

        if (review.isLike()) {
            youTuber.decreaseLikeReviewCount();
            return;
        }
        youTuber.decreaseNoReviewCount();
    }
}
