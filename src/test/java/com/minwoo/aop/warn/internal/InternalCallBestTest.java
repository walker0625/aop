package com.minwoo.aop.warn.internal;

import com.minwoo.aop.warn.InternalCallAspect;
import com.minwoo.aop.warn.InternalCallBest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

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