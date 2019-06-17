package com.mashup.thing.user;

import com.mashup.thing.exception.BaseException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @MockBean
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Test
    public void testDuplicateAddUser() {
        doThrow(new DataIntegrityViolationException("중복")).when(userRepository).save(any());

        expectedException.expect(BaseException.class);
        expectedException.expect(hasProperty("code", is(HttpStatus.CONFLICT)));

        ReqSignUpUserDto reqSignUpUserDto = new ReqSignUpUserDto();
        reqSignUpUserDto.setNickName("thing");
        reqSignUpUserDto.setUid("123");
        reqSignUpUserDto.setDateBirth(1990);

        userService.addUser(reqSignUpUserDto);
    }

}
