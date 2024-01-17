package com.minwoo.aop.warn;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
//@Component
public class InternalCallSelf {

    private InternalCallSelf internalCallBySelf;

    // 생성자 주입으로 자신을 받으려고 하면 순환참조가 발생하여 setter 주입 사용
    // SpringBoot 2.6부터 순환참조를 기본적으로 금지하여 설정 필요
    // properties = "spring.main.allow-circular-references=true"
    @Autowired
    public void setInternalCallBySelf(InternalCallSelf internalCallBySelf) {
        this.internalCallBySelf = internalCallBySelf;
    }

    public void externalCall() {
        log.info("external call");
        internalCallBySelf.internalCall();
    }

    public void internalCall() {
        log.info("internal Call");
    }

}