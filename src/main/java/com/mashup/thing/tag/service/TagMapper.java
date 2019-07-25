package com.mashup.thing.tag.service;

import com.mashup.thing.tag.ResTagsDto;
import com.mashup.thing.tag.domain.Tag;
import com.mashup.thing.tag.domain.TagType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TagMapper {

    public ResTagsDto toResTagsDto(List<Tag> tags) {
        List<ResTagsDto.ResTagDto> tagDtos = new ArrayList<>();

        for (TagType tagType : TagType.values()) {
            tagDtos.add(ResTagsDto.ResTagDto.builder()
                    .category(tagType.getTagType())
                    .list(new ArrayList<>())
                    .build());
        }

        for (Tag tag : tags) {
            tagDtos.get(tag.getMainTagIndex())
                    .addSubTag(tag.getSubTag());
        }

        return ResTagsDto.builder()
                .tags(tagDtos)
                .build();
    }
}
