package com.minwoo.aop.member;

import com.minwoo.aop.annotation.ClassAop;
import com.minwoo.aop.annotation.MethodAop;
import org.springframework.stereotype.Component;

@ClassAop
@Component
public class MemberServiceImpl implements MemberService{

    @Override
    @MethodAop("test value")
    public String hello(String param) {
        return "OK";
    }

    public String internal(String param) {
        return "OK";
    }

}