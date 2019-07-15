package com.mashup.thing.review;

import com.mashup.thing.exception.user.NotFoundUserException;
import com.mashup.thing.exception.youtuber.NotFoundYouTuBerException;
import com.mashup.thing.review.domain.Liked;
import com.mashup.thing.review.domain.Review;
import com.mashup.thing.review.dto.ReqWriteReviewDto;
import com.mashup.thing.user.UserRepository;
import com.mashup.thing.user.domain.User;
import com.mashup.thing.youtuber.YouTuberRepository;
import com.mashup.thing.youtuber.domain.YouTuber;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WriteReviewService {

    private final UserRepository userRepository;
    private final YouTuberRepository youTuberRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public WriteReviewService(UserRepository userRepository,
                              YouTuberRepository youTuberRepository,
                              ReviewRepository reviewRepository,
                              ReviewMapper reviewMapper) {
        this.userRepository = userRepository;
        this.youTuberRepository = youTuberRepository;
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
    }

    @Transactional
    public void write(Long youTuberId, ReqWriteReviewDto reqWriteReviewDto) {
        User user = userRepository.findById(reqWriteReviewDto.getUserId()).orElseThrow(NotFoundUserException::new);

        YouTuber youTuber = youTuberRepository.findById(youTuberId).orElseThrow(NotFoundYouTuBerException::new);
        Review review = reviewMapper.toReview(youTuber, user, reqWriteReviewDto);

        reviewRepository.save(review);

        if (isLike(reqWriteReviewDto)) {
            youTuber.plusLikeReviewCount();
            return;
        }

        youTuber.plusNoReviewCount();

    }

    private Boolean isLike(ReqWriteReviewDto reqWriteReviewDto) {
        return reqWriteReviewDto.getLiked().equals(Liked.LIKE.getLike());
    }

}
