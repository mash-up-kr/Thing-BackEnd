package com.mashup.thing.tag.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ResTagDto {

    private String category;
    private List<String> list;

    public void addSubTag(String subTag) {
        this.list.add(subTag);
    }
}
