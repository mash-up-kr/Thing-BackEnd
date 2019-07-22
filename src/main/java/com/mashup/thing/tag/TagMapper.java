package com.mashup.thing.tag;

import com.mashup.thing.tag.domain.Tag;
import com.mashup.thing.tag.domain.TagType;
import com.mashup.thing.tag.dto.ResTagDto;
import com.mashup.thing.tag.dto.ResTagsDto;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TagMapper {

    public ResTagsDto toResTagsDto(List<Tag> tags) {
        List<ResTagDto> tagDtos = new ArrayList<>();

        for (TagType tagType : TagType.values()) {
            tagDtos.add(ResTagDto.builder()
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
