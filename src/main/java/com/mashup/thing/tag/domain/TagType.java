package com.mashup.thing.tag.domain;

public enum TagType {

    COMMON("공통", 0), GAME("게임", 1),
    BEAUTY("뷰티", 2), SPORTS("스포츠", 3),
    ENTERTAINMENT("엔터테인먼트", 4), MUSIC("뮤직", 5),
    DAILY("일상", 6), KNOWLEDGE("지식", 7),
    KIDS("키즈", 8), PET("펫", 9),
    FOOD("푸드", 10);

    private String tagType;
    private Integer index;

    TagType(String tagType, Integer index) {
        this.tagType = tagType;
        this.index = index;
    }

    public String getTagType() {
        return tagType;
    }

    public Integer getIndex() {
        return index;
    }
}
