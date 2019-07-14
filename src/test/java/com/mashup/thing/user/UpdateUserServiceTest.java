package com.mashup.thing.user;

import com.mashup.thing.exception.user.ExistNicknameException;
import com.mashup.thing.user.domain.User;
import com.mashup.thing.user.dto.ReqUpdateUserDto;
import com.mashup.thing.user.service.S3Uploader;
import com.mashup.thing.user.service.UpdateUserService;
import com.mashup.thing.user.service.UserMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UpdateUserServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private S3Uploader s3Uploader;
    private UserRepository userRepository;
    private UserMapper userMapper;
    private UpdateUserService updateUserService;

    @Before
    public void mockUp() {
        userRepository = mock(UserRepository.class);
        s3Uploader = mock(S3Uploader.class);
        userMapper = mock(UserMapper.class);

        updateUserService = new UpdateUserService(s3Uploader, userRepository, userMapper);
    }

    @Test
    public void testSameUserNickName() {
        when(userRepository.findById(any()))
                .thenReturn(Optional.of(new User("uid", "testOne", 1993, 1)));
        when(userRepository.existsByNickName(any())).thenReturn(true);

        expectedException.expect(ExistNicknameException.class);
        ReqUpdateUserDto reqSignUpUserDto = new ReqUpdateUserDto();
        reqSignUpUserDto.setNickname("testTwo");
        Long userId = 1L;

        updateUserService.update(reqSignUpUserDto, userId);
    }
}

