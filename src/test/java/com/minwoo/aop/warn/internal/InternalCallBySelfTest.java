package com.minwoo.aop.warn.internal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

// SpringBoot 2.6부터 순환참조를 기본적으로 금지하여 설정 필요
@SpringBootTest(properties = "spring.main.allow-circular-references=true")
@Import(InternalCallAspect.class)
class InternalCallBySelfTest {

    @Autowired
    InternalCallSelf internalCallSelf;

    @Test
    void test() {
        internalCallSelf.externalCall();
    }

    @Test
    void test2() {
        internalCallSelf.internalCall();
    }

}