package com.mashup.thing.user.dto;

import com.mashup.thing.category.domain.CategoryType;
import lombok.Getter;

@Getter
public class ResCategoryDto {
    private Long id;
    private String categoryType;

    private ResCategoryDto(CategoryType category) {
        this.id = category.getPrimaryKey();
        this.categoryType = category.getCategoryType();
    }

    public static ResCategoryDto from(CategoryType category) {
        return new ResCategoryDto(category);
    }
}
