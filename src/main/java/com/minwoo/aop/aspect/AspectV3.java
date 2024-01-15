package com.minwoo.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class AspectV3 {

    @Pointcut("execution(* com.minwoo.aop.order..*(..)))")
    private void allOrder(){} // pointcut signature : 재사용이 가능한 장점

    @Pointcut("execution(* *..*Service.*(..))") // Interface도 적용됨
    private void allService(){} // pointcut signature : 재사용이 가능한 장점

    @Around("allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable{
        log.info("[log] {}", joinPoint.getSignature());
        return joinPoint.proceed();
    }

    @Around("allOrder() && allService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable{

        try {
            log.info("transaction start : {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            log.info("transaction commit : {}", joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            log.info("transaction rollback : {}", joinPoint.getSignature());
            throw e;
        } finally {
            log.info("resource release : {}", joinPoint.getSignature());
        }

    }

}