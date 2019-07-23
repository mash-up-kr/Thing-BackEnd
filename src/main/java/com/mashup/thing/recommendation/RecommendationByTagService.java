package com.mashup.thing.recommendation;

import com.mashup.thing.exception.user.NotFoundUserException;
import com.mashup.thing.recommendation.dto.ReqRecommendationDto;
import com.mashup.thing.recommendation.dto.ResRecommendationDto;
import com.mashup.thing.user.UserRepository;
import com.mashup.thing.user.domain.User;
import com.mashup.thing.youtuber.YouTuberRepository;
import com.mashup.thing.youtuber.YouTuberSpec;
import com.mashup.thing.youtuber.domain.YouTuber;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class RecommendationByTagService {
    private final UserRepository userRepository;
    private final YouTuberRepository youTuberRepository;
    private final RecommendationMapper recommendationMapper;

    public RecommendationByTagService(UserRepository userRepository,
                                      YouTuberRepository youTuberRepository,
                                      RecommendationMapper recommendationMapper) {
        this.userRepository = userRepository;
        this.youTuberRepository = youTuberRepository;
        this.recommendationMapper = recommendationMapper;
    }

    @Transactional
    public ResRecommendationDto searchByTag(String uid, ReqRecommendationDto reqRecommendationDto) {

        User user = userRepository.findByUid(uid).orElseThrow(NotFoundUserException::new);

        user.updateSearchTag(reqRecommendationDto.getCommon(), reqRecommendationDto.getCategory());

        Specification<YouTuber> spec = YouTuberSpec.isTags(
                reqRecommendationDto.getCommon(), reqRecommendationDto.getCategory());

        List<YouTuber> youTubers = youTuberRepository.findAll(spec);

        if (youTubers.isEmpty()) {
            return recommendationMapper.toNoneYouTuber();
        }

        return recommendationMapper.toResRecommendationDto(youTubers);

    }
}
