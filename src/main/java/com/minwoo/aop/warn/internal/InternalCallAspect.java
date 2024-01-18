package com.minwoo.aop.warn.internal;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Slf4j
@Aspect
public class InternalCallAspect {

    //@Before("execution(* com.minwoo.aop.warn.internal.InternalCall.*(..))")
    //@Before("execution(* com.minwoo.aop.warn.*.*(..))")
    @Before("execution(* com.minwoo.aop.warn..*.*(..))")
    public void doCall(JoinPoint joinPoint) {
        log.info("[call] {}", joinPoint.getSignature());
    }

}