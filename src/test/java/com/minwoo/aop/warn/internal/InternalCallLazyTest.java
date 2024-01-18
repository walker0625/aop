package com.minwoo.aop.warn.internal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(InternalCallAspect.class)
class InternalCallLazyTest {

    @Autowired
    InternalCallLazy internalCallLazy;

    @Test
    void test() {
        internalCallLazy.externalCall();
    }

    @Test
    void test2() {
        internalCallLazy.internalCall();
    }

}