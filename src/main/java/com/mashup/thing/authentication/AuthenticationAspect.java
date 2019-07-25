package com.mashup.thing.authentication;

import com.mashup.thing.exception.aop.FailAuthenticationException;
import com.mashup.thing.user.domain.UserRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
class AuthenticationAspect {

    private final UserRepository userRepository;

    public AuthenticationAspect(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Before(value = "execution(public * com.mashup.thing.*.*Controller.*(String,..))")
    public void checkUidValid(JoinPoint joinPoint) {
        String uid = (String) joinPoint.getArgs()[0];

        if (isNotUser(uid)) {
            throw new FailAuthenticationException();
        }
    }

    private Boolean isNotUser(String uid) {
        return !(userRepository.existsByUid(uid));
    }
}
