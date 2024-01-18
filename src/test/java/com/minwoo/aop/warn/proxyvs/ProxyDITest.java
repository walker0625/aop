package com.minwoo.aop.warn.proxyvs;

import com.minwoo.aop.member.MemberService;
import com.minwoo.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest// (properties = "spring.aop.proxy-target-class=false") // JDK 동적 프록시로 생성
@Import(ProxyDIAspect.class)
public class ProxyDITest {

    @Autowired
    MemberService memberService;

    // JDK 동적 프록시로 생성하는 경우, bean에 등록된 proxy가 interface만 알고 있어 impl에 주입 불가
    @Autowired
    MemberServiceImpl memberServiceImpl;

    @Test
    void run() {
        log.info("memberService class={}", memberService.getClass());
        log.info("memberServiceImpl class={}", memberServiceImpl.getClass());
        memberServiceImpl.hello("hello");
    }

}
