package com.mashup.thing.user.dto;


import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ResSignInDto {

    private Long id;
    private String nickName;
    private String uid;
    private Integer dateBirth;
    private String profileUrl;
    private String gender;
    private List<ResCategoryDto> categories;
    private List<String> commonTag;
    private List<String> categoryTag;

    @Getter
    @Builder
    public static class ResCategoryDto {
        private Long id;
        private String categoryType;

    }

}
