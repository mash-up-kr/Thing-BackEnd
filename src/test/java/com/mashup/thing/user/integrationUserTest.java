package com.mashup.thing.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = "test")
public class integrationUserTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void testSignUpUser() {
        ReqSignUpUserDto reqSignUpUserDto = new ReqSignUpUserDto();
        reqSignUpUserDto.setNickName("thing");
        reqSignUpUserDto.setUid("123");
        reqSignUpUserDto.setDateBirth(1990);

        ResponseEntity<Void> response = restTemplate.postForEntity("/v1/users", reqSignUpUserDto, Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    public void testSignUpConflictUser() {
        ReqSignUpUserDto reqSignUpUserDto = new ReqSignUpUserDto();
        reqSignUpUserDto.setNickName("thing");
        reqSignUpUserDto.setUid("123");
        reqSignUpUserDto.setDateBirth(1990);

        ResponseEntity<Void> responseTwo = restTemplate.postForEntity("/v1/users", reqSignUpUserDto, Void.class);
        assertThat(responseTwo.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
    }


}
