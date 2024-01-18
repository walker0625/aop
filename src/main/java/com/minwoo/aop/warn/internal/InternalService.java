package com.minwoo.aop.warn.internal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InternalService {

    public void internalCall() {
        log.info("internal Call");
    }

}
