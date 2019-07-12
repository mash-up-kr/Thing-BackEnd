package com.mashup.thing.endpage;

import com.mashup.thing.endpage.dto.ResEndPageDto;
import com.mashup.thing.exception.BaseException;
import com.mashup.thing.review.ReviewRepository;
import com.mashup.thing.review.domain.Liked;
import com.mashup.thing.review.domain.Review;
import com.mashup.thing.user.UserRepository;
import com.mashup.thing.user.domain.User;
import com.mashup.thing.video.VideoRepository;
import com.mashup.thing.video.domain.Video;
import com.mashup.thing.youtuber.YouTuberRepository;
import com.mashup.thing.youtuber.domain.YouTuber;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EndPageService {
    private final ReviewRepository reviewRepository;
    private final VideoRepository videoRepository;
    private final YouTuberRepository youTuberRepository;
    private final UserRepository userRepository;
    private final EndPageMapper endPageMapper;

    private final static String SORT_PUBLISHED_AT = "publishedAt";
    private final static String SORT_CREATE_AT = "createAt";

    public EndPageService(ReviewRepository reviewRepository,
                          VideoRepository videoRepository,
                          YouTuberRepository youTuberRepository,
                          UserRepository userRepository, EndPageMapper endPageMapper) {
        this.reviewRepository = reviewRepository;
        this.videoRepository = videoRepository;
        this.youTuberRepository = youTuberRepository;
        this.userRepository = userRepository;
        this.endPageMapper = endPageMapper;
    }

    public ResEndPageDto getEndPage(Long youTuberId, String uid) {

        YouTuber youTuber = youTuberRepository.findById(youTuberId)
                .orElseThrow(() -> new BaseException(HttpStatus.BAD_REQUEST));

        User user = userRepository.findByUid(uid)
                .orElseThrow(() -> new BaseException(HttpStatus.BAD_REQUEST));

        List<Video> videos = videoRepository.findAllByYouTuberId(youTuberId,
                PageRequest.of(0, 10, new Sort(Sort.Direction.DESC, SORT_PUBLISHED_AT)));

        List<Review> likeReview = reviewRepository.findAllByYouTuberIdAndLiked(youTuberId, Liked.LIKE,
                PageRequest.of(0, 3, new Sort(Sort.Direction.DESC, SORT_CREATE_AT)));

        List<Review> noReview = reviewRepository.findAllByYouTuberIdAndLiked(youTuberId, Liked.NO,
                PageRequest.of(0, 3, new Sort(Sort.Direction.DESC, SORT_CREATE_AT)));

        return endPageMapper.toEndPage(youTuber, videos, likeReview, noReview, user);
    }
}
