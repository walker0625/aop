package com.minwoo.aop.warn.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class InternalCallBest {

    // inner 로직을 별도의 bean으로 분리
    private final InternalService internalService;

    public void externalCall() {
        log.info("external call");
        internalService.internalCall();
    }

}