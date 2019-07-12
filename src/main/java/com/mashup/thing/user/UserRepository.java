package com.mashup.thing.user;

import com.mashup.thing.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUid(String uid);

    Boolean existsByUid(String uid);

    Boolean existsByNickName(String nickName);
}
