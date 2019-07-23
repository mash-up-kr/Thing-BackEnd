package com.mashup.thing.recommendation;

import com.mashup.thing.exception.user.NotFoundUserException;
import com.mashup.thing.recommendation.dto.ReqRecommendationDto;
import com.mashup.thing.recommendation.dto.ResHomeDto;
import com.mashup.thing.recommendation.dto.ResRecommendationDto;
import com.mashup.thing.user.UserRepository;
import com.mashup.thing.user.domain.User;
import com.mashup.thing.youtuber.YouTuberRepository;
import com.mashup.thing.youtuber.YouTuberSpec;
import com.mashup.thing.youtuber.domain.YouTuber;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class SearchRecommendationService {
    private final UserRepository userRepository;
    private final YouTuberRepository youTuberRepository;
    private final RecommendationMapper recommendationMapper;

    public SearchRecommendationService(UserRepository userRepository,
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

    public ResHomeDto searchByUser(String uid) {
        User user = userRepository.findByUid(uid).orElseThrow(NotFoundUserException::new);

        Page<YouTuber> soaringYouTubers = youTuberRepository.findByOrderBySoaringDesc(PageRequest.of(0, 3));

        List<YouTuber> youTubers = new ArrayList<>();

        if (user.isTag()) {
            Specification<YouTuber> spec = YouTuberSpec.isTags(user.getCommonTag(), user.getCategoryTag());
            youTubers = youTuberRepository.findAll(spec);
        }

        if (youTubers.isEmpty()) {
            return recommendationMapper.toSoaringYouTuber(soaringYouTubers);
        }

        return recommendationMapper.toResHomeDto(youTubers, soaringYouTubers);
    }
}
