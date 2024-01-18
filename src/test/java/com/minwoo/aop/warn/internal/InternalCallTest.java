package com.minwoo.aop.warn.internal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(InternalCallAspect.class)
class InternalCallTest {

    @Autowired
    InternalCall internalCall;

    @Test
    void test() {
        // 내부에서 실행되는 internalCall()에 aop가 적용되지 않음
        // proxy 호출은 externalCall()만 되기 때문에
        internalCall.externalCall();
    }

    @Test
    void test2() {
        internalCall.internalCall();
    }

}