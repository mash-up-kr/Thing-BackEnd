package com.mashup.thing.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashup.thing.exception.BaseException;
import com.mashup.thing.user.dto.ReqSignUpUserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void TestConflictAddUser() throws Exception {
        doThrow(new BaseException(HttpStatus.CONFLICT)).when(userService).addUser(any());

        ReqSignUpUserDto reqSignUpUserDto = new ReqSignUpUserDto();
        reqSignUpUserDto.setNickName("thing");
        reqSignUpUserDto.setUid("123");
        reqSignUpUserDto.setDateBirth(1990);

        this.mockMvc.perform(post("/v1/users")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(objectMapper.writeValueAsString(reqSignUpUserDto)))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    public void TestNullFieldAddUser() throws Exception {
        ReqSignUpUserDto reqSignUpUserDto = new ReqSignUpUserDto();
        reqSignUpUserDto.setDateBirth(1990);

        this.mockMvc.perform(post("/v1/users")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(reqSignUpUserDto)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void TestNullHeaderSingInUser() throws Exception {

        this.mockMvc.perform(get("/v1/sign-in")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

}
