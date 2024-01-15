package com.minwoo.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

@Slf4j
public class AspectV5 {

    @Aspect
    @Order(2) // Aspect 단위로 Order 적용이 가능하여 class 형태로 분리(bean도 각각 등록해야 함)
    public static class LogAspect {
        @Around("com.minwoo.aop.aspect.Pointcuts.allOrder()")
        public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable{
            log.info("[log] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }

    @Aspect
    @Order(1)
    public static class TxAspect {
        @Around("com.minwoo.aop.aspect.Pointcuts.orderAndService()")
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

}