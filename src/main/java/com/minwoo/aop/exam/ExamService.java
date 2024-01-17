package com.minwoo.aop.exam;

import com.minwoo.aop.annotation.Retry;
import com.minwoo.aop.annotation.Trace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;

    @Retry
    @Trace
    public void request(String itemId) {
        examRepository.save(itemId);
    }

}
