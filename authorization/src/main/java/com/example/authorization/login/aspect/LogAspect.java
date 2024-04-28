package com.example.authorization.login.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Around;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;

@Aspect  // Point 1: @Aspect
@Component
public class LogAspect {

    // Point 2: AOP implementation
    @Before("execution(* com.example.authorization.login.controller.LoginController.getLogin(..))")
    public void startLog(JoinPoint jp) {
        System.out.println("Method start: " + jp.getSignature());
    }

    // Point 2: AOP implementation
    @After("execution(* com.example.authorization.login.controller.LoginController.getLogin(..))")
    public void endLog(JoinPoint jp) {
        System.out.println("Method end: " + jp.getSignature());
    }

    // Aspect for log output of Dao class.
//    @Around("execution(∗ ∗..∗.∗UserDao∗.∗(..))")
    @Around("execution(* com.example.authorization.login.domain.repository.*.*(..))")
    public Object daoLog(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("Method start: " + jp.getSignature());
        try {
            Object result = jp.proceed();
            System.out.println("Method end: " + jp.getSignature());
            return result;
        } catch (Exception e) {
            System.out.println("Method abnormal termination: " + jp.getSignature());
            e.printStackTrace();
            throw e;
        }
    }
}
