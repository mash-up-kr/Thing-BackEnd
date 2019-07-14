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

    @Lob
    @Column(name="profile_url", length=512)
    private String profileUrl;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public User(String uid, String nickName, Integer dateBirth, Integer gender) {
        this.uid = uid;
        this.nickName = nickName;
        this.dateBirth = dateBirth;
        this.gender = Gender.from(gender);
    }

    public void removeImg() {
        this.profileUrl = null;
    }

    public void updateNickname(String nickName) {
        this.nickName = nickName;
    }

    public void updateInfo(Integer dateBirth, Integer gender, String profileUrl) {
        this.dateBirth = dateBirth;
        this.gender = Gender.from(gender);
        this.profileUrl = profileUrl;
    }

    public Boolean isNotSameNickname(String nickName) {
        return !this.nickName.equals(nickName);
    }
}


