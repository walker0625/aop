package com.minwoo.aop.exam;

import com.minwoo.aop.annotation.Retry;
import com.minwoo.aop.annotation.Trace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class ExamRepository {

    private static int seq = 0;

    @Trace
    //@Retry(2)
    public String save(String itemId) {
        seq++;
        log.info("seq = {}", seq); // 6으로 넘어가면서 성공
        if (seq % 5 == 0) {
            throw new IllegalStateException("run fail");
        }
        return "ok";
    }

}