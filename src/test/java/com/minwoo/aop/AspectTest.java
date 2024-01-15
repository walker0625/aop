package com.minwoo.aop;

import com.minwoo.aop.order.OrderRepository;
import com.minwoo.aop.order.OrderService;
import com.minwoo.aop.aspect.AspectV6;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest
//@Import({AspectV5.LogAspect.class, AspectV5.TxAspect.class}) // bean으로 등록됨
@Import(AspectV6.class)
public class AspectTest {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    void aopInfo() {
        log.info(String.valueOf(AopUtils.isAopProxy(orderService)));
        log.info(String.valueOf(AopUtils.isAopProxy(orderRepository)));
    }

    @Test
    void success() {
        orderService.orderItem("itemA");
    }

    @Test
    void exception() {
        Assertions.assertThatThrownBy(() -> orderService.orderItem("ex"))
                  .isInstanceOf(IllegalStateException.class);
    }

}