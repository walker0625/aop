package com.minwoo.aop.warn;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InternalCall {

    public void externalCall() {
        log.info("external call");
        internalCall();
    }

    public void internalCall() {
        log.info("internal Call");
    }

}