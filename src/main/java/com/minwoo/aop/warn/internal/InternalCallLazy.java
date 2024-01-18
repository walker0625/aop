package com.minwoo.aop.warn.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class InternalCallLazy {

    // InternalCallLazy 정보를 가지고만 있고 생성할 때 사용하지 않아 순환참조 발생하지 않음
    private final ObjectProvider<InternalCallLazy> callLazyProvider;

    public void externalCall() {
        log.info("external call");
        InternalCallLazy internalCallLazy = callLazyProvider.getObject(); // 이 시점에 load 하므로 지연 가능
        internalCallLazy.internalCall();
    }

    public void internalCall() {
        log.info("internal Call");
    }

}