package com.mashup.thing.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
//사용자 정보 수정
public class UpdateUserDto {


    @NotNull
    private String nickName;
    private Integer dateBirth;
    private Integer gender;
    private String imageUrl;

    public UpdateUserDto ( String nickName, Integer dateBirth ,Integer gender , String imageUrl){

        this.nickName = nickName;
        this.dateBirth = dateBirth;
        this.gender = gender;
        this.imageUrl = imageUrl;
    }
}
