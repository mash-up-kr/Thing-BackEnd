package com.mashup.thing.youtuber;

import com.mashup.thing.youtuber.dto.ResEndPageDto;
import org.springframework.stereotype.Service;

@Service
public class YouTuberService {
    private final EndPageYouTuberService endPageYouTuberService;

    public YouTuberService(EndPageYouTuberService endPageYouTuberService) {
        this.endPageYouTuberService = endPageYouTuberService;
    }

    public ResEndPageDto getEndPageYouTuber(Long youTuberId) {
        return endPageYouTuberService.getEndPage(youTuberId);
    }
}
