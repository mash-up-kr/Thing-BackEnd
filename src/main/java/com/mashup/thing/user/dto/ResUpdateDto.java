package com.mashup.thing.user.dto;

import lombok.Getter;

@Getter
public class ResUpdateDto {

    private Long id;
    private Integer dateBirth;
    private String gender;
    private String nickName;
    private String profileUrl;


    public ResUpdateDto(Long id, Integer dateBirth,
                        String gender, String nickName,
                        String profileUrl){

        this.id = id;
        this.dateBirth = dateBirth;
        this.gender = gender;
        this.nickName = nickName;
        this.profileUrl = profileUrl;
    }
}
