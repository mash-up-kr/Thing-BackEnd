package com.mashup.thing.user;

import java.util.Arrays;

enum Gender {
    MAN(1), WOMAN(2), ETC(3), NONE(4);

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