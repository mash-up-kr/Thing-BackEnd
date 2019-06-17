package com.mashup.thing.user;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    @Test
    public void testCreateUser(){
        ReqSignUpUserDto reqSignUpUserDto = new ReqSignUpUserDto();
        reqSignUpUserDto.setNickName("thing");
        reqSignUpUserDto.setUid("123");
        reqSignUpUserDto.setDateBirth(1990);

        User user = User.from(reqSignUpUserDto);

        assertThat(user.getGender()).isEqualTo(Gender.NONE);
    }

}
