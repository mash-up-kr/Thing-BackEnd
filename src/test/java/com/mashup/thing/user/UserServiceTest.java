package com.mashup.thing.user;

import com.mashup.thing.exception.user.ExistNicknameException;
import com.mashup.thing.exception.user.ExistUidException;
import com.mashup.thing.user.domain.UserRepository;
import com.mashup.thing.user.dto.ReqSignUpUserDto;
import com.mashup.thing.user.service.SignInUserService;
import com.mashup.thing.user.service.UpdateUserService;
import com.mashup.thing.user.service.UserMapper;
import com.mashup.thing.user.service.UserService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private UserService userService;
    private UserRepository userRepository;
    private UpdateUserService updateUserService;
    private UserMapper userMapper;
    private SignInUserService signInUserService;

    @Before
    public void mockUp() {
        userRepository = mock(UserRepository.class);
        updateUserService = mock(UpdateUserService.class);
        userMapper = mock(UserMapper.class);
        signInUserService = mock(SignInUserService.class);

        userService = new UserService(userRepository, signInUserService, updateUserService, userMapper);
    }

    @Test
    public void testAddExistUserUid() {
        when(userRepository.existsByUid(any())).thenReturn(true);

        expectedException.expect(ExistUidException.class);

        ReqSignUpUserDto reqSignUpUserDto = new ReqSignUpUserDto();
        reqSignUpUserDto.setUid("123");

        userService.addUser(reqSignUpUserDto);
    }

    @Test
    public void testAddExistUserNickName() {
        when(userRepository.existsByNickName(any())).thenReturn(true);

        expectedException.expect(ExistNicknameException.class);

        ReqSignUpUserDto reqSignUpUserDto = new ReqSignUpUserDto();
        reqSignUpUserDto.setNickname("thing");

        userService.addUser(reqSignUpUserDto);
    }


}
