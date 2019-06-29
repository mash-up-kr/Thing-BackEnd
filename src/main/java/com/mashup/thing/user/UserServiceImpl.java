package com.mashup.thing.user;

import com.mashup.thing.exception.BaseException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserDao userDao;


    public UserServiceImpl(UserRepository userRepository ,UserDao userDao) {
        this.userRepository = userRepository;
        this.userDao = userDao;
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


    @Override
    public FindUserDto findUser(String uid){
        User user = userDao.findUser(uid);
        FindUserDto findUserDto= new FindUserDto(user.getUid(),user.getNickName(),user.getDateBirth(),user.getGender().getGender(),user.getImageUrl());

        return findUserDto;

    }

    @Override
    public void updateUser (UpdateUserDto updateUserDto,String uid)
    {
            userDao.validateUid(uid);
            userDao.updateUser(uid,updateUserDto.getNickName(), updateUserDto.getGender().toString(), updateUserDto.getDateBirth(),updateUserDto.getImageUrl());
            //중복 검사

    }

}
