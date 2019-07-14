package com.mashup.thing.user;

import com.mashup.thing.exception.RequestNullFieldException;
import com.mashup.thing.user.dto.ReqSignUpUserDto;
import com.mashup.thing.user.dto.ReqUpdateUserDto;
import com.mashup.thing.user.dto.ResSignInDto;
import com.mashup.thing.user.dto.ResUpdateDto;
import com.mashup.thing.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "USER SIGN IN", notes = "USER SIGN IN API")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "USER 로그인 성공"),
            @ApiResponse(code = 400, message = "ID - NULL or 없는 ID"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @GetMapping("/v1/sign-in")
    public ResponseEntity<ResSignInDto> signInUser(@RequestHeader(value = "uid") String uid) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.signInUser(uid));
    }

    @ApiOperation(value = "USER SIGN UP", notes = "USER SIGN UP API - Gender(Enum) : WOMAN - 0, MAN - 1, ETC - 2")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "USER 등록 성공"),
            @ApiResponse(code = 400, message = "UID or NickName - NULL"),
            @ApiResponse(code = 409, message = "중복된 닉네임 또는 아이디"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @PostMapping("/v1/users")
    public ResponseEntity<Void> signUpUser(@RequestBody @Valid ReqSignUpUserDto reqSignUpUserDto
            , BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new RequestNullFieldException();
        }
        userService.addUser(reqSignUpUserDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "USER UPDATE", notes = "USER UPDATE API - Gender(Enum) : WOMAN - 0, MAN - 1, ETC - 2")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "USER 업데이트 성공"),
            @ApiResponse(code = 403, message = "인증 실패"),
            @ApiResponse(code = 400, message = "NickName - NULL"),
            @ApiResponse(code = 409, message = "중복된 닉네임"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @PatchMapping("/v1/users/{userId}")
    public ResponseEntity<ResUpdateDto> updateUser(@RequestHeader(value = "uid") String uid,
                                                   @PathVariable(value = "userId") Long userId,
                                                   @ModelAttribute @Valid ReqUpdateUserDto reqSignUpUserDto,
                                                   BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new RequestNullFieldException();
        }
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(reqSignUpUserDto, userId));
    }

}
