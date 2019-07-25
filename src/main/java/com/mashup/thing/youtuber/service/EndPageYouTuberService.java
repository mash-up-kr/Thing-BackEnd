package com.mashup.thing.youtuber.service;

import com.mashup.thing.exception.aop.FailAuthenticationException;
import com.mashup.thing.exception.youtuber.NotFoundYouTuBerException;
import com.mashup.thing.review.domain.Liked;
import com.mashup.thing.review.domain.Review;
import com.mashup.thing.review.domain.ReviewRepository;
import com.mashup.thing.user.domain.User;
import com.mashup.thing.user.domain.UserRepository;
import com.mashup.thing.video.domain.Video;
import com.mashup.thing.video.domain.VideoRepository;
import com.mashup.thing.youtuber.domain.YouTuber;
import com.mashup.thing.youtuber.domain.YouTuberRepository;
import com.mashup.thing.youtuber.dto.ResEndPageDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EndPageYouTuberService {
    private final ReviewRepository reviewRepository;
    private final VideoRepository videoRepository;
    private final YouTuberRepository youTuberRepository;
    private final UserRepository userRepository;
    private final YouTuberMapper youTuberMapper;

    private final static String SORT_PUBLISHED_AT = "publishedAt";
    private final static String SORT_CREATE_AT = "createAt";

    public EndPageYouTuberService(ReviewRepository reviewRepository,
                                  VideoRepository videoRepository,
                                  YouTuberRepository youTuberRepository,
                                  UserRepository userRepository,
                                  YouTuberMapper youTuberMapper) {
        this.reviewRepository = reviewRepository;
        this.videoRepository = videoRepository;
        this.youTuberRepository = youTuberRepository;
        this.userRepository = userRepository;
        this.youTuberMapper = youTuberMapper;
    }

    public ResEndPageDto getEndPage(Long youTuberId, String uid) {
        User user = userRepository.findByUid(uid)
                .orElseThrow(FailAuthenticationException::new);

        YouTuber youTuber = youTuberRepository.findById(youTuberId)
                .orElseThrow(NotFoundYouTuBerException::new);

        List<Video> videos = videoRepository.findAllByYouTuberId(youTuberId,
                PageRequest.of(0, 10, new Sort(Sort.Direction.DESC, SORT_PUBLISHED_AT)));

        List<Review> likeReview = reviewRepository.findAllByYouTuberIdAndLiked(youTuberId, Liked.LIKE,
                PageRequest.of(0, 3, new Sort(Sort.Direction.DESC, SORT_CREATE_AT)));

        List<Review> noReview = reviewRepository.findAllByYouTuberIdAndLiked(youTuberId, Liked.NO,
                PageRequest.of(0, 3, new Sort(Sort.Direction.DESC, SORT_CREATE_AT)));

        return youTuberMapper.toEndPage(youTuber, videos, likeReview, noReview, user.getId());
    }
}
