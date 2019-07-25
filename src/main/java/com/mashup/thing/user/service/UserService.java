package com.mashup.thing.user.service;

import com.mashup.thing.exception.user.ExistNicknameException;
import com.mashup.thing.exception.user.ExistUidException;
import com.mashup.thing.user.domain.UserRepository;
import com.mashup.thing.user.domain.User;
import com.mashup.thing.user.dto.ReqSignUpUserDto;
import com.mashup.thing.user.dto.ReqUpdateUserDto;
import com.mashup.thing.user.dto.ResSignInDto;
import com.mashup.thing.user.dto.ResUpdateDto;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final SignInUserService signInUserService;
    private final UpdateUserService updateUserService;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository,
                       SignInUserService signInUserService,
                       UpdateUserService updateUserService,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.signInUserService = signInUserService;
        this.updateUserService = updateUserService;
        this.userMapper = userMapper;
    }

    public void addUser(ReqSignUpUserDto reqSignUpUserDto) {
        if (isUid(reqSignUpUserDto.getUid())) {
            throw new ExistUidException();
        }

        if (isNickName(reqSignUpUserDto.getNickname())) {
            throw new ExistNicknameException();
        }

        User user = userMapper.toUser(reqSignUpUserDto);
        userRepository.save(user);
    }

    public ResSignInDto signInUser(String uid) {
        return signInUserService.signIn(uid);
    }

    public ResUpdateDto updateUser(@Valid ReqUpdateUserDto reqSignUpUserDto, Long userId) {
       return updateUserService.update(reqSignUpUserDto, userId);
    }

    private Boolean isUid(String uid) {
        return userRepository.existsByUid(uid);
    }

    private Boolean isNickName(String nickName) {
        return userRepository.existsByNickName(nickName);
    }


}
