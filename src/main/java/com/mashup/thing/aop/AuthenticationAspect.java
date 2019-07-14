package com.mashup.thing.aop;

import com.mashup.thing.exception.aop.FailAuthenticationException;
import com.mashup.thing.exception.aop.FailIdAuthenticationException;
import com.mashup.thing.user.UserRepository;
import com.mashup.thing.user.domain.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthenticationAspect {

    private final UserRepository userRepository;

    public AuthenticationAspect(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Pointcut("execution(public * com.mashup.thing.ranking.RankingController.*(..))")
    public void rankingController() {
    }

    @Pointcut("execution(public * com.mashup.thing.endpage.EndPageController.*(..))")
    public void endPageController() {
    }

    @Pointcut("execution(public * com.mashup.thing.user.UserController.updateUser(..))")
    public void userController() {
    }

    @Pointcut("endPageController()||rankingController()")
    public void serviceController() {
    }

    @Before(value = "serviceController()")
    public void checkSessionValid(JoinPoint joinPoint) {
        String uid = (String) joinPoint.getArgs()[0];

        if (isNotUser(uid)) {
            throw new FailAuthenticationException();
        }
    }

    @Before(value = "userController()")
    public void checkUserValid(JoinPoint joinPoint) {
        String uid = (String) joinPoint.getArgs()[0];
        Long userId = (Long) joinPoint.getArgs()[1];

        User user = userRepository.findByUid(uid).orElseThrow(FailAuthenticationException::new);

        if (user.isNotSameId(userId)) {
            throw new FailIdAuthenticationException();
        }
    }

    private Boolean isNotUser(String uid) {
        return !(userRepository.existsByUid(uid));
    }
}
