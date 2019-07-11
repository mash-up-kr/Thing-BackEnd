package com.mashup.thing.category.domain;

public enum CategoryType {

    KNOWLEDGE("지식", 1L), SPORTS("스포츠", 2L),
    BEAUTY("뷰티", 3L), KIDS("키즈", 4L),
    TALK("토크", 5L), DAILY("일상", 6L),
    GAME("게임", 7L), FOOD("푸드", 8L),
    ENTERTAINMENT("엔터테이먼트", 9L), MUSIC("뮤직", 10L),
    COMIC("코믹", 11L);

    private String categoryType;
    private Long primaryKey;

    CategoryType(String categoryType, Long primaryKey) {
        this.categoryType = categoryType;
        this.primaryKey = primaryKey;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public Long getPrimaryKey() { return primaryKey;}
}
