package com.mashup.thing.tag;

import com.mashup.thing.tag.domain.Tag;
import com.mashup.thing.tag.dto.ResTagsDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    public TagService(TagRepository tagRepository, TagMapper tagMapper) {
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
    }

    public ResTagsDto getTags() {
        List<Tag> tags = tagRepository.findAll();

        return tagMapper.toResTagsDto(tags);
    }
}
