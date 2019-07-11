package com.mashup.thing.user;

import com.mashup.thing.category.domain.CategoryType;
import com.mashup.thing.search.domain.Search;
import com.mashup.thing.user.domain.User;
import com.mashup.thing.user.dto.ReqSignUpUserDto;
import com.mashup.thing.user.dto.ResCategoryDto;
import com.mashup.thing.user.dto.ResSearchDto;
import com.mashup.thing.user.dto.ResSignInDto;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public ResSignInDto toResSignInDto(User user, List<Search> searches) {
        return new ResSignInDto(user.getId(),
                user.getNickName(),
                user.getUid(), user.getDateBirth(),
                Optional.ofNullable(user.getProfileUrl()).orElse(""),
                user.getGender().toString(),
                searches.stream().map(ResSearchDto::from).collect(Collectors.toList()),
                Arrays.stream(CategoryType.values()).map(ResCategoryDto::from).collect(Collectors.toList()));
    }

    public User toUser(ReqSignUpUserDto reqSignUpUserDto) {
        return new User(reqSignUpUserDto.getUid(),
                reqSignUpUserDto.getNickName(),
                reqSignUpUserDto.getDateBirth(),
                reqSignUpUserDto.getGender());
    }
}
