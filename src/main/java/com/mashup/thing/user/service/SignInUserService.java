package com.mashup.thing.user.service;

import com.mashup.thing.exception.user.NotFoundUserException;
import com.mashup.thing.user.UserRepository;
import com.mashup.thing.user.domain.User;
import com.mashup.thing.user.dto.ResSignInDto;
import org.springframework.stereotype.Service;

@Service
public class SignInUserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public SignInUserService(UserRepository userRepository,
                             UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public ResSignInDto signIn(String uid) {
        User user = userRepository.findByUid(uid).orElseThrow(NotFoundUserException::new);
        return userMapper.toResSignInDto(user);
    }

}
