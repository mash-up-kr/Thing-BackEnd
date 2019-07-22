package com.mashup.thing.tag.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ResTagsDto {

    private List<ResTagDto> tags;
}
