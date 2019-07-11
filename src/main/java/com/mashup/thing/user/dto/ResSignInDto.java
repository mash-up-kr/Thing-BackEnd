package com.mashup.thing.user.dto;


import lombok.Getter;

import java.util.List;

@Getter
public class ResSignInDto {

    private Long id;
    private String nickName;
    private String uid;
    private Integer dateBirth;
    private String profileUrl;
    private String gender;
    private List<ResSearchDto> resSearches;
    private List<ResCategoryDto> resCategories;


    public ResSignInDto(Long id, String nickName, String uid,
                        Integer dateBirth, String profileUrl, String gender,
                        List<ResSearchDto> resSearches, List<ResCategoryDto> resCategories) {
        this.id = id;
        this.nickName = nickName;
        this.uid = uid;
        this.dateBirth = dateBirth;
        this.profileUrl = profileUrl;
        this.gender = gender;
        this.resSearches = resSearches;
        this.resCategories = resCategories;
    }
}
