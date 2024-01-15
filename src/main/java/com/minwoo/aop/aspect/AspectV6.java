package com.minwoo.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;


@Slf4j
@Aspect
public class AspectV6 {

    // 가장 강력하며 자주 쓰이는 방식
    // <다른 세부 annotation이 존재하는 이유>
    // 1. proceed()를 호출하지 않는 경우 방지 2. 명확하게 의도를 드러내기 위해서
    // @Around("com.minwoo.aop.aspect.Pointcuts.orderAndService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable{

        try {
            log.info("transaction start : {}", joinPoint.getSignature());
            //@Before

            // 실행하지 않으면 메인 logic 미동작(반복 실행 가능)
            Object result = joinPoint.proceed();

            log.info("transaction commit : {}", joinPoint.getSignature());

            //@AfterReturning
            return result; // 변환 가능
        } catch (Exception e) {
            log.info("transaction rollback : {}", joinPoint.getSignature());

            //@AfterThrowing
            throw e;
        } finally {
            log.info("resource release : {}", joinPoint.getSignature());
            //@After
        }

    }

    @Before("com.minwoo.aop.aspect.Pointcuts.orderAndService()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("[@Before] {}", joinPoint.getSignature());
    }

    @AfterThrowing(value = "com.minwoo.aop.aspect.Pointcuts.orderAndService()",
                   throwing = "ex")
    public void doThrowing(JoinPoint joinPoint, Exception ex){
        log.info("[@AfterThrowing] {} ex = {}", joinPoint.getSignature(), ex.getMessage());
    }

    @AfterReturning(value = "com.minwoo.aop.aspect.Pointcuts.orderAndService()",
                    returning = "result")
    // 변환은 불가(result 자체가 고정 타입 - 파라미터에서 다른 타입으로 받으면 해당 pointcut 자체가 미동작)
    public void doReturn(JoinPoint joinPoint, String result) { // returning = "result"와 일치
        // 조작(기존 형에서 변형)은 가능
        result = result + " test";

        log.info("[@AfterReturning] {} return = {}", joinPoint.getSignature(), result);
    }

    @After("com.minwoo.aop.aspect.Pointcuts.orderAndService()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("[@After] {}", joinPoint.getSignature());
    }

}