package com.minwoo.aop;

import com.minwoo.aop.annotation.ClassAop;
import com.minwoo.aop.annotation.MethodAop;
import com.minwoo.aop.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(ParameterTest.ParameterAspect.class)
@SpringBootTest
public class ParameterTest {

    @Autowired
    MemberService memberService;

    @Test
    void success() {
        log.info("memberService Proxy = {}", memberService.getClass());
        memberService.hello("helloA");
    }

    @Slf4j
    @Aspect
    static class ParameterAspect {
        
        @Pointcut("execution(* com.minwoo.aop.member..*.*(..))")
        private void allMember() {}
        
        @Around("allMember()") 
        public Object logArgs1(ProceedingJoinPoint joinPoint) throws Throwable {
            Object arg1 = joinPoint.getArgs()[0];
            log.info("[logArgs1] {}, arg = {}", joinPoint.getSignature(), arg1);
            return  joinPoint.proceed();
        }

        @Around("allMember() && args(arg, ..)") // ..  : Object... 으로 받아서 사용
        public Object logArgs2(ProceedingJoinPoint joinPoint, Object arg) throws Throwable {
            log.info("[logArgs2] {}, arg = {}", joinPoint.getSignature(), arg);
            return joinPoint.proceed();
        }

        @Before("allMember() && args(arg, ..)")
        public void logArgs3(String arg) throws Throwable {
            log.info("[logArgs3] arg = {}", arg);
        }

        // this : 빈으로 등록된 객체(proxy)
        @Before("allMember() && this(obj)")
        public void thisArgs(JoinPoint joinPoint, MemberService obj) throws Throwable {
            log.info("[this] {}, this = {}", joinPoint.getSignature(), obj.getClass());
        }

        // target : proxy가 실제 호출하는 대상
        @Before("allMember() && target(obj)")
        public void targetArgs(JoinPoint joinPoint, MemberService obj) throws Throwable {
            log.info("[target] {}, target = {}", joinPoint.getSignature(), obj.getClass());
        }

        @Before("allMember() && @target(annotation)")
        public void annotationArgs(JoinPoint joinPoint, ClassAop annotation) throws Throwable {
            log.info("[@target] {}, annotation = {}", joinPoint.getSignature(), annotation);
        }

        @Before("allMember() && @within(annotation)")
        public void withinArgs(JoinPoint joinPoint, ClassAop annotation) throws Throwable {
            log.info("[@within] {}, annotation = {}", joinPoint.getSignature(), annotation);
        }

        @Before("allMember() && @annotation(annotation)")
        public void withinArgs(JoinPoint joinPoint, MethodAop annotation) throws Throwable {
            log.info("[@annotation] {}, annotation value = {}", joinPoint.getSignature(), annotation.value());
        }

    }

}
