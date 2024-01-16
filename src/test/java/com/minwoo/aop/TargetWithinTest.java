package com.minwoo.aop;

import com.minwoo.aop.annotation.ClassAop;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest
@Import(TargetWithinTest.Config.class)
public class TargetWithinTest {

    @Autowired
    Child child;

    @Test
    void success() {
        child.childMethod();
        //child.parentMethod();
    }

    static class Parent {
        public void parentMethod(){}
    }

    @ClassAop
    static class Child extends Parent {
        public void childMethod(){}
    }

    static class Config {

        @Bean
        public Child child() {
            return new Child();
        }

        @Bean
        public TargetWithinAspect targetWithinAspect() {
            return new TargetWithinAspect();
        }

    }

    @Slf4j
    @Aspect
    static class TargetWithinAspect {

        // @Around("@target(com.minwoo.aop.annotation.ClassAop)")
        // 위와 같이 단독으로 사용하는 것은 적용이 불가한 final bean(Spring 설정 등)에도 시도하다가 문제가 발생하므로
        // execution() 등으로 적용 범위를 좁히고 arg/@arg/@target 등을 적용하는 것이 가능

        // @target : 선택된 인스턴스(ClassAop 어노테이션을 가진 bean(Child))의 부모(Parent)까지 적용
        @Around("execution(* com.minwoo.aop..*(..)) && @target(com.minwoo.aop.annotation.ClassAop)")
        public Object atTarget(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[@target] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

        // @within : 선택된 클래스(ClassAop 어노테이션을 가진 Child) 내부에 있는 메소드만 적용
        @Around("execution(* com.minwoo.aop..*(..)) && @within(com.minwoo.aop.annotation.ClassAop)")
        public Object atWithin(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[@within] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

    }

}