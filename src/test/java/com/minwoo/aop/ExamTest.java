package com.minwoo.aop;

import com.minwoo.aop.aspect.RetryAspect;
import com.minwoo.aop.aspect.TraceAspect;
import com.minwoo.aop.exam.ExamService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@Import({TraceAspect.class, RetryAspect.class})
@SpringBootTest
public class ExamTest {

    @Autowired
    ExamService examService;

    @Test
    void test() {
        for (int i = 0; i < 5; i++) {
            log.info("request count" + (i + 1));
            examService.request("test");
        }
    }

}
