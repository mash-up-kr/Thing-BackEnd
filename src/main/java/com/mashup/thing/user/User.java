package com.mashup.thing.user;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nickName;

    @Column(unique = true)
    private String uid;

    private Integer dateBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private User(ReqSignUpUserDto reqSignUpUserDto) {
        this.uid = reqSignUpUserDto.getUid();
        this.nickName = reqSignUpUserDto.getNickName();
        this.dateBirth = reqSignUpUserDto.getDateBirth();
        this.gender = Gender.from(reqSignUpUserDto.getGender());
    }

    public static User from(ReqSignUpUserDto reqSignUpUserDto) {
        return new User(reqSignUpUserDto);
    }
}


