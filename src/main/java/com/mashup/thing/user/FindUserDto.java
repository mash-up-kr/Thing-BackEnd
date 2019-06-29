package com.mashup.thing.user;

import lombok.Getter;
import lombok.Setter;
import java.lang.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FindUserDto {

    private String uid;

    @NotNull
    private String nickName;
    private Integer dateBirth;
    private Integer gender;
    private String imageUrl;

    public FindUserDto(String uid , String nickName, Integer dateBirth ,Integer gender , String imageUrl){
        this.uid = uid;
        this.nickName = nickName;
        this.dateBirth = dateBirth;
        this.gender = gender;
        this.imageUrl = imageUrl;
    }
}

