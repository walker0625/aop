package com.minwoo.aop;

import com.minwoo.aop.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(ThisTargetTest.ThisTargetAspect.class)
@SpringBootTest(properties = "spring.aop.proxy-target-class=false") // JDK 동적 프록시 생성(기본값 : CGLIB)
public class ThisTargetTest {

    @Autowired
    MemberService memberService;

    @Test
    void success() {
        log.info("memberService Proxy = {}", memberService.getClass());
        memberService.hello("helloA");
    }

    @Slf4j
    @Aspect
    static class ThisTargetAspect {

        @Around("this(com.minwoo.aop.member.MemberService)")
        public Object doThisInter(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[this-inter] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

        @Around("target(com.minwoo.aop.member.MemberService)")
        public Object doTargetInter(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[target-inter] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

        // jdk 동적 프록시로 생성하면 MemberService만이 proxy로 등록되어 aop 적용되지 않음
        @Around("this(com.minwoo.aop.member.MemberServiceImpl)")
        public Object doThisImpl(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[this-impl] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

        @Around("target(com.minwoo.aop.member.MemberServiceImpl)")
        public Object doTargetImpl(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[target-impl] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

    }

}