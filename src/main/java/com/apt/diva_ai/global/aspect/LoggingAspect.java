package com.apt.diva_ai.global.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("execution(* com.apt.diva_ai.domain.inference.controller.*.*(..))")
    public void method() {
    }

    @Before("method()")
    public void before(JoinPoint joinPoint) {
        String signature =
            joinPoint.getClass().getName() + "." + joinPoint.getSignature().getName();
        log.info("\u001B[100m ### 컨트롤러 호출 : {}", signature);
    }

    @After("method()")
    public void after(JoinPoint joinPoint) {
        String signature =
            joinPoint.getClass().getName() + "." + joinPoint.getSignature().getName();
        log.info("\u001B[100m ### 컨트롤러 종료 : {}", signature);
    }
}
