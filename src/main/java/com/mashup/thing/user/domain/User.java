package com.mashup.thing.user.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
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

    private String profileUrl;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public User(String uid, String nickName, Integer dateBirth, Integer gender) {
        this.uid = uid;
        this.nickName = nickName;
        this.dateBirth = dateBirth;
        this.gender = Gender.from(gender);
    }
}


