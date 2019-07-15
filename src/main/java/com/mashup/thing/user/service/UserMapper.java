package com.mashup.thing.user.service;

import com.mashup.thing.category.domain.CategoryType;
import com.mashup.thing.search.domain.Search;
import com.mashup.thing.user.domain.User;
import com.mashup.thing.user.dto.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public ResSignInDto toResSignInDto(User user, List<Search> searches) {
        return ResSignInDto.builder()
                .id(user.getId())
                .uid(user.getUid())
                .dateBirth(user.getDateBirth())
                .profileUrl(Optional.ofNullable(user.getProfileUrl()).orElse(""))
                .gender(user.getGender().toString())
                .searches(searches.stream()
                        .map(search -> ResSearchDto.builder()
                                .id(search.getId())
                                .createAt(search.getCreateAt())
                                .text(search.getText())
                                .build())
                        .collect(Collectors.toList()))
                .categories(Arrays.stream(CategoryType.values())
                        .map(categoryType -> ResCategoryDto.builder()
                                .id(categoryType.getPrimaryKey())
                                .categoryType(categoryType.getCategoryType())
                                .build())
                        .collect(Collectors.toList()))
                .build();
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
