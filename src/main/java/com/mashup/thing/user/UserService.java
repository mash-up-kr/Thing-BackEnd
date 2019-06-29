package com.mashup.thing.user;

public interface UserService {
    void addUser(ReqSignUpUserDto reqSignUpUserDto);

    FindUserDto findUser(String uid);

   void updateUser(UpdateUserDto updateUserDto ,String uid);

}
