package com.minwoo.aop.warn.proxyvs;

import com.minwoo.aop.member.MemberService;
import com.minwoo.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
public class ProxyCastingTest {

    @Test
    void jdkProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(false); // JDK 동적 프록시

        // proxy를 interface로 캐스팅 가능
        MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();

        // class jdk.proxy2.$Proxy9 -> MemberService 기반
        log.info(memberServiceProxy.getClass().toString());

        // impl로 casting이 불가(JDK 동적 프록시는 interface만을 알고 있어 impl 변환 불가)
        assertThrows(ClassCastException.class, () -> {
            MemberServiceImpl castingMemberServiceProxy = (MemberServiceImpl) memberServiceProxy;
        });
    }

    // Spring Boot 2.0부터 기본값은 "CGLIB"로
    // Spring 4.0부터 추가된 "objenesis" 라이브러리를 통해
    // CGLIB의 문제였던 1. 기본 생성자 필수 2. 생성자 2번 호출 문제를 해결
    // 단 final 클래스/메소드에 AOP(Proxy) 적용은 불가하다(CGLIB는 상속을 통해 Proxy를 생성하므로)
    @Test
    void cglibProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true); // CGLIB(기본값)

        // impl을 기반으로 만들어진 proxy
        MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();

        // com.minwoo.aop.member.MemberServiceImpl$$SpringCGLIB$$0 -> MemberServiceImpl 기반
        log.info(memberServiceProxy.getClass().toString());

        // impl로 casting이 가능
        MemberServiceImpl castingMemberServiceProxy = (MemberServiceImpl) memberServiceProxy;
    }

}
