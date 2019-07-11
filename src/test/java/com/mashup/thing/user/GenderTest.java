package com.mashup.thing.user;

import com.mashup.thing.user.domain.Gender;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GenderTest {

    @Test
    public void testValueOfGender() {
        Gender gender = Gender.from(1);
        assertThat(gender).isEqualTo(Gender.MAN);
    }
}
