package com.mashup.thing.user.service;

import com.mashup.thing.exception.user.ExistNicknameException;
import com.mashup.thing.exception.user.NotFoundUserException;
import com.mashup.thing.user.domain.UserRepository;
import com.mashup.thing.user.domain.User;
import com.mashup.thing.user.dto.ReqUpdateUserDto;
import com.mashup.thing.user.dto.ResUpdateDto;
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
    public ResUpdateDto update(ReqUpdateUserDto reqUpdateUserDto, Long userId) {

        User user = userRepository.findById(userId).orElseThrow(NotFoundUserException::new);

        if (user.isNotSameNickname(reqUpdateUserDto.getNickname())) {
            isSameDifferentUserNickname(reqUpdateUserDto.getNickname());
            user.updateNickname(reqUpdateUserDto.getNickname());
        }

        if (isImgFile(reqUpdateUserDto.getImgFile())) {
            String imgUrl = s3Uploader.upload(reqUpdateUserDto.getImgFile(), user.getUid());
            reqUpdateUserDto.setImgUrl(imgUrl);
        }

        if (isNoneImgUrl(reqUpdateUserDto.getImgUrl())) {
            user.removeImg();
        }
        user.updateInfo(reqUpdateUserDto.getDateBirth(),
                reqUpdateUserDto.getGender(),
                reqUpdateUserDto.getImgUrl());

        return userMapper.toResUpdateDto(user);
    }

    private void isSameDifferentUserNickname(String nickname) {
        if (userRepository.existsByNickName(nickname)) {
            throw new ExistNicknameException();
        }
    }

    private Boolean isImgFile(MultipartFile imgFIle) {
        return Optional.ofNullable(imgFIle).isPresent();
    }

    private Boolean isNoneImgUrl(String imgUrl) {
        return !Optional.ofNullable(imgUrl).isPresent();
    }
}
