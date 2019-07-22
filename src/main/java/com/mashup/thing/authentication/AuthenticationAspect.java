package com.mashup.thing.authentication;

import com.mashup.thing.exception.aop.FailAuthenticationException;
import com.mashup.thing.user.UserRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
class AuthenticationAspect {

    private final UserRepository userRepository;

    public AuthenticationAspect(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Pointcut("execution(public * com.mashup.thing.ranking.RankingController.*(..))")
    public void rankingController() {
    }

    @Pointcut("execution(public * com.mashup.thing.user.UserController.updateUser(..))")
    public void userController() {
    }

    @Pointcut("execution(public * com.mashup.thing.review.ReviewController.*(..))")
    public void reviewController() {
    }

    @Pointcut("rankingController()||userController()||reviewController()")
    public void serviceController() {
    }

    @Before(value = "serviceController()")
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
