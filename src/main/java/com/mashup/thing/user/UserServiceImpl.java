package com.mashup.thing.user;

import com.mashup.thing.exception.BaseException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(ReqSignUpUserDto reqSignUpUserDto) {
        User user = User.from(reqSignUpUserDto);

        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException dive) {
            throw new BaseException(HttpStatus.CONFLICT);
        }
    }
}
