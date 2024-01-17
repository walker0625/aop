package com.minwoo.aop.warn;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InternalCallLazy {

    private final ObjectProvider<InternalCallLazy> callLazyProvider;

    // InternalCallLazy 생성 이후에 주입
    public InternalCallLazy(ObjectProvider<InternalCallLazy> callLazyProvider) {
        this.callLazyProvider = callLazyProvider;
    }

    public void externalCall() {
        log.info("external call");
        InternalCallLazy internalCallLazy = callLazyProvider.getObject(); // 이 시점에 load 하므로 지연 가능
        internalCallLazy.internalCall();
    }

    public void internalCall() {
        log.info("internal Call");
    }

}