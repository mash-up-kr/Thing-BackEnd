package com.mashup.thing.user.service;

import com.mashup.thing.exception.BaseException;
import com.mashup.thing.user.UserRepository;
import com.mashup.thing.user.domain.User;
import com.mashup.thing.user.dto.ReqUpdateUserDto;
import com.mashup.thing.user.dto.ResUpdateDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class UpdateUserService {
    private final S3Uploader s3Uploader;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UpdateUserService(S3Uploader s3Uploader,
                             UserRepository userRepository,
                             UserMapper userMapper) {
        this.s3Uploader = s3Uploader;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    public ResUpdateDto update(ReqUpdateUserDto reqSignUpUserDto, String uid) {

        User user = userRepository.findByUid(uid).orElseThrow(() -> new BaseException(HttpStatus.BAD_REQUEST));

        if (user.isNotSameNickname(reqSignUpUserDto.getNickname())) {
            differentUserSameNickname(reqSignUpUserDto.getNickname());
            user.updateNickname(reqSignUpUserDto.getNickname());
        }

        if (isImgFile(reqSignUpUserDto.getImgFile())) {
            String imgUrl = s3Uploader.upload(reqSignUpUserDto.getImgFile(), user.getUid());
            reqSignUpUserDto.setImgUrl(imgUrl);
        }

        if (isNoneImgUrl(reqSignUpUserDto.getImgUrl())) {
            user.removeImg();
        }

        user.updateInfo(reqSignUpUserDto.getDateBirth(),
                reqSignUpUserDto.getGender(),
                reqSignUpUserDto.getImgUrl());

        return userMapper.toResUpdateDto(user);
    }

    private void differentUserSameNickname(String nickname) {
        if (userRepository.existsByNickName(nickname)) {
            throw new BaseException(HttpStatus.BAD_REQUEST);
        }
    }

    private Boolean isImgFile(MultipartFile imgFIle) {
        return Optional.ofNullable(imgFIle).isPresent();
    }

    private Boolean isNoneImgUrl(String imgUrl) {
        return !Optional.ofNullable(imgUrl).isPresent();
    }
}
