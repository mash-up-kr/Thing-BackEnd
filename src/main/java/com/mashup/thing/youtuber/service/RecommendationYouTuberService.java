package com.mashup.thing.youtuber.service;

import com.mashup.thing.exception.user.NotFoundUserException;
import com.mashup.thing.youtuber.dto.ReqRecommendationDto;
import com.mashup.thing.youtuber.dto.ResHomeDto;
import com.mashup.thing.youtuber.dto.ResRecommendationDto;
import com.mashup.thing.user.domain.UserRepository;
import com.mashup.thing.user.domain.User;
import com.mashup.thing.youtuber.domain.YouTuberRepository;
import com.mashup.thing.youtuber.domain.YouTuberSpec;
import com.mashup.thing.youtuber.domain.YouTuber;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class RecommendationYouTuberService {
    private final UserRepository userRepository;
    private final YouTuberRepository youTuberRepository;
    private final YouTuberMapper youTuberMapper;

    public RecommendationYouTuberService(UserRepository userRepository,
                                         YouTuberRepository youTuberRepository,
                                         YouTuberMapper youTuberMapper) {
        this.userRepository = userRepository;
        this.youTuberRepository = youTuberRepository;
        this.youTuberMapper = youTuberMapper;
    }

    @Transactional
    public ResRecommendationDto searchByTag(String uid, ReqRecommendationDto reqRecommendationDto) {

        User user = userRepository.findByUid(uid).orElseThrow(NotFoundUserException::new);

        user.updateSearchTag(reqRecommendationDto.getCommon(), reqRecommendationDto.getCategory());

        Specification<YouTuber> spec = YouTuberSpec.isTags(
                reqRecommendationDto.getCommon(), reqRecommendationDto.getCategory());

        List<YouTuber> youTubers = youTuberRepository.findAll(spec);

        if (youTubers.isEmpty()) {
            return youTuberMapper.toNoneYouTuber();
        }

        return youTuberMapper.toResRecommendationDto(youTubers);

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
            return youTuberMapper.toSoaringYouTuber(soaringYouTubers);
        }

        return youTuberMapper.toResHomeDto(youTubers, soaringYouTubers);
    }
}
