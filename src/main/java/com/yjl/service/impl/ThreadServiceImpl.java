package com.yjl.service.impl;

import com.yjl.service.facade.ThreadService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author unbroken
 * @Description TODO
 * @Version 1.0
 * @date 2024/4/4 10:04
 */
@Service
public class ThreadServiceImpl implements ThreadService {
    @Async("myAsyncExecutor")
    @Override
    public String asyncUpdate() {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("结束执行异步线程,名称为:" + Thread.currentThread().getName());
        return null;
    }
}
