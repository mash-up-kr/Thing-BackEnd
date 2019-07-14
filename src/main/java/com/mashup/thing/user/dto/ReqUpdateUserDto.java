package com.mashup.thing.user.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ReqUpdateUserDto {

    private MultipartFile imgFile;
    private String imgUrl;
    @NotNull
    private String nickname;
    private Integer dateBirth;
    private Integer gender;
}
