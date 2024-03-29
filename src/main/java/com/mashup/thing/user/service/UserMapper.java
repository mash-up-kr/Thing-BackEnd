package com.mashup.thing.user.service;

import com.mashup.thing.category.domain.CategoryType;
import com.mashup.thing.user.domain.User;
import com.mashup.thing.user.dto.ReqSignUpUserDto;
import com.mashup.thing.user.dto.ResSignInDto;
import com.mashup.thing.user.dto.ResUpdateDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public ResSignInDto toResSignInDto(User user) {
        return ResSignInDto.builder()
                .id(user.getId())
                .uid(user.getUid())
                .nickName(user.getNickName())
                .dateBirth(user.getDateBirth())
                .profileUrl(Optional.ofNullable(user.getProfileUrl()).orElse(""))
                .gender(user.getGender().toString())
                .categories(Arrays.stream(CategoryType.values())
                        .map(categoryType -> ResSignInDto.ResCategoryDto.builder()
                                .id(categoryType.getPrimaryKey())
                                .categoryType(categoryType.getCategoryType())
                                .build())
                        .collect(Collectors.toList()))
                .commonTag(toTagList(user.getCommonTag()))
                .categoryTag(toTagList(user.getCategoryTag()))
                .build();
    }

    private List<String> toTagList(String tag) {
        if(tag == null) {
            return new ArrayList<>();
        }
        return Arrays.stream(tag.split(",")).collect(Collectors.toList());
    }


    public User toUser(ReqSignUpUserDto reqSignUpUserDto) {
        return new User(reqSignUpUserDto.getUid(),
                reqSignUpUserDto.getNickname(),
                reqSignUpUserDto.getDateBirth(),
                reqSignUpUserDto.getGender());
    }

    public ResUpdateDto toResUpdateDto(User user) {
        return ResUpdateDto.builder()
                .id(user.getId())
                .dateBirth(Optional.ofNullable(user.getDateBirth()).orElse(0))
                .gender(user.getGender().toString())
                .nickName(user.getNickName())
                .profileUrl(Optional.ofNullable(user.getProfileUrl()).orElse(""))
                .build();
    }
}
