package com.mashup.thing.tag;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ResTagsDto {

    private List<ResTagDto> tags;

    @Getter
    @Builder
    public static class ResTagDto {

        private String category;
        private List<String> list;

        public void addSubTag(String subTag) {
            this.list.add(subTag);
        }
    }

}
