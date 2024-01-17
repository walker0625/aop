package com.minwoo.aop.aspect;

import com.minwoo.aop.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class RetryAspect {

    @Around("@annotation(retry)") // 파라미터의 타입 정보로 설정됨
    public Object doRetry(ProceedingJoinPoint joinPoint, Retry retry) throws Throwable {

        log.info("[retry] {} retry = {}", joinPoint.getSignature(), retry);
        int maxRetry = retry.value();
        Exception exceptionHolder = null;

        for (int retryCount = 1; retryCount <= maxRetry; retryCount++) {
            try {
                log.info("[retry] try count={}/{}", retryCount, maxRetry);
                log.info("[joinPoint - retry] joinPoint={}", joinPoint.getSignature());
                return joinPoint.proceed();
            } catch (Exception e) {
                log.info("[retry exception] e={}", e.getMessage());
                log.info("[joinPoint - exception] joinPoint={}", joinPoint.getSignature());
                exceptionHolder = e;
            }
        }

        throw exceptionHolder;
    }

}
