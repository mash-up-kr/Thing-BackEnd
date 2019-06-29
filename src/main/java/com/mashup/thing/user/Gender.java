package com.mashup.thing.user;

import java.util.Arrays;

enum Gender {
    WOMAN(0), MAN(1), ETC(2), NONE(3);

    private Integer gender;

    Gender(Integer gender) {
        this.gender = gender;
    }

    public Integer getGender() {
        return gender;
    }

    public static Gender from(Integer genderNum) {
        return Arrays.stream(Gender.values())
                .filter(gender -> gender.hasGenderCode(genderNum))
                .findAny()
                .orElse(NONE);
    }

    private boolean hasGenderCode(Integer genderNum) {
        return gender.equals(genderNum);
    }

}