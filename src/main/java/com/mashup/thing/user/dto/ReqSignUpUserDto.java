package com.mashup.thing.user.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ReqSignUpUserDto {

    @NotNull
    private String uid;

    @NotNull
    private String nickName;

    private Integer dateBirth;

    private Integer gender;
}
