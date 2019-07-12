package com.mashup.thing.user;

import com.mashup.thing.user.domain.Gender;
import com.mashup.thing.user.domain.User;
import com.mashup.thing.user.dto.ReqSignUpUserDto;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserMapperTest {

    @Test
    public void testToUser(){
        UserMapper userMapper = new UserMapper();
        ReqSignUpUserDto reqSignUpUserDto = new ReqSignUpUserDto();
        reqSignUpUserDto.setNickname("thing");
        reqSignUpUserDto.setUid("123");
        reqSignUpUserDto.setDateBirth(1990);

        User user = userMapper.toUser(reqSignUpUserDto);

        assertThat(user.getGender()).isEqualTo(Gender.NONE);
    }

}
