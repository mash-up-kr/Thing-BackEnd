package com.mashup.thing.youtuber;

import com.mashup.thing.exception.BaseException;
import com.mashup.thing.review.ReviewRepository;
import com.mashup.thing.review.domain.Liked;
import com.mashup.thing.review.domain.Review;
import com.mashup.thing.video.VideoRepository;
import com.mashup.thing.video.domain.Video;
import com.mashup.thing.youtuber.domain.YouTuber;
import com.mashup.thing.youtuber.dto.ResEndPageDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EndPageYouTuberService {
    private final ReviewRepository reviewRepository;
    private final VideoRepository videoRepository;
    private final YouTuberRepository youTuberRepository;
    private final YouTuberMapper youTuberMapper;

    public EndPageYouTuberService(ReviewRepository reviewRepository,
                                  VideoRepository videoRepository,
                                  YouTuberRepository youTuberRepository,
                                  YouTuberMapper youTuberMapper) {
        this.reviewRepository = reviewRepository;
        this.videoRepository = videoRepository;
        this.youTuberRepository = youTuberRepository;
        this.youTuberMapper = youTuberMapper;
    }

    public ResEndPageDto getEndPage(Long youTuberId) {

        YouTuber youTuber = youTuberRepository.findById(youTuberId)
                .orElseThrow(() -> new BaseException(HttpStatus.BAD_REQUEST));

        List<Video> videos = videoRepository.findAllByYouTuberId(youTuberId,
                PageRequest.of(0, 10, new Sort(Sort.Direction.DESC, "publishedAt")));

        List<Review> likeReview = reviewRepository.findAllByYouTuberIdAndLiked(youTuberId, Liked.LIKE,
                PageRequest.of(0, 3, new Sort(Sort.Direction.DESC, "createAt")));

        List<Review> noReview = reviewRepository.findAllByYouTuberIdAndLiked(youTuberId, Liked.NO,
                PageRequest.of(0, 3, new Sort(Sort.Direction.DESC, "createAt")));

        return youTuberMapper.toEndPage(youTuber, videos, likeReview, noReview);
    }
}
