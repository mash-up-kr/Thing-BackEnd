package com.mashup.thing.category.domain;

import com.mashup.thing.exception.category.NotFoundCategoryTypeException;

import java.util.Arrays;

public enum CategoryType {


    TOTAL("전체", 1L), GAME("게임", 2L),
    BEAUTY("뷰티", 3L), SPORTS("스포츠", 4L),
    ENTERTAINMENT("엔터테인먼트", 5L), MUSIC("뮤직", 6L),
    DAILY("일상", 7L), KNOWLEDGE("지식", 8L),
    KIDS("키즈", 9L), PET("펫", 10L),
    FOOD("푸드", 11L);

    private String categoryType;
    private Long primaryKey;

    CategoryType(String categoryType, Long primaryKey) {
        this.categoryType = categoryType;
        this.primaryKey = primaryKey;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public Long getPrimaryKey() {
        return primaryKey;
    }

    public static CategoryType findByCategory(Long primaryKey) {
        return Arrays.stream(CategoryType.values())
                .filter(categoryType -> categoryType.hasCategory(primaryKey))
                .findAny()
                .orElseThrow(NotFoundCategoryTypeException::new);
    }

    public boolean hasCategory(Long primaryKey) {
        return this.primaryKey.equals(primaryKey);
    }
}
