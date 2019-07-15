package com.mashup.thing.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResUpdateDto {

    private Long id;
    private Integer dateBirth;
    private String gender;
    private String nickName;
    private String profileUrl;

}
