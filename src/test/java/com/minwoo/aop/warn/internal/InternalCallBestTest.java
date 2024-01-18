package com.minwoo.aop.warn.internal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(InternalCallAspect.class)
class InternalCallBestTest {

    @Autowired
    InternalCallBest internalCallBest;

    @Test
    void externalCall() {
        internalCallBest.externalCall();
    }

}