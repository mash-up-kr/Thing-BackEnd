package com.mashup.thing.user;

import com.mashup.thing.exception.BaseException;
import com.mashup.thing.user.domain.User;
import com.mashup.thing.user.dto.ReqSignUpUserDto;
import com.mashup.thing.user.dto.ResSignInDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final SignInUserService signInUserService;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository,
                       SignInUserService signInUserService,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.signInUserService = signInUserService;
        this.userMapper = userMapper;
    }

    public void addUser(ReqSignUpUserDto reqSignUpUserDto) {
        if (isUid(reqSignUpUserDto.getUid())) {
            throw new BaseException(HttpStatus.CONFLICT);
        }

        User user = userMapper.toUser(reqSignUpUserDto);
        userRepository.save(user);
    }

    public ResSignInDto signInUser(String uid) {
        return signInUserService.signIn(uid);
    }

    private Boolean isUid(String uid) {
        return userRepository.existsByUid(uid);
    }
}
