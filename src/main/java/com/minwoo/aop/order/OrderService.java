package com.minwoo.aop.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public String orderItem(String itemId) {
        log.info("[orderService run]");
        return orderRepository.save(itemId);
    }

}
