package com.mashup.thing.aop;

import com.mashup.thing.exception.BaseException;
import com.mashup.thing.user.UserRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthenticationAspect {

    private final UserRepository userRepository;

    public AuthenticationAspect(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Pointcut("execution(public * com.mashup.thing.ranking.RankingController.*(..))")
    public void rankingController() {}

    @Before(value = "rankingController()")
    public void checkSessionValid(JoinPoint joinPoint) {
        String uid = (String)joinPoint.getArgs()[0];
        if(isNotUser(uid)) {
            throw new BaseException(HttpStatus.BAD_REQUEST);
        }

    }

    private Boolean isNotUser(String uid) {
        return !(userRepository.existsByUid(uid));
    }
}
