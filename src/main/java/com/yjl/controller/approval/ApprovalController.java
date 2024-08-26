package com.yjl.controller.approval;

import com.yjl.annotation.RecordAnnotation;
import com.yjl.domain.model.ApprovalCallback;
import com.yjl.domain.enums.OperationType;
import com.yjl.service.facade.ApprovalService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;


/**
 * @author unbroken
 * @Description AOP和自定义注解
 * @Version 1.0
 * @date 2024/2/11 23:36
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/approval")
public class ApprovalController {

    private final ApprovalService entityService;
    @Async
    @RecordAnnotation(operationType = OperationType.ADD)
    @PostMapping("/callback")
    public void callback(@RequestBody ApprovalCallback callback) {
        System.out.println("记录日志");
        entityService.callback(callback);
    }

    public static void main(String[] args) {
        new MyThread().start();
//        new Thread(() -> {
//            System.out.println("我的线程1");
//        }).start();
        MyRunnable myRunnable = new MyRunnable();
        new Thread(myRunnable).start();
        System.out.println("主线程");
        MyCallable myCallable = new MyCallable();
        FutureTask futureTask = new FutureTask<>(myCallable);
        new Thread(futureTask).start();
    }
    static class MyThread extends Thread{
        public void run() {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("我的线程");
        }
    }
    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("我的runnable");
        }
    }

    static class MyCallable implements Callable{

        @Override
        public Object call() throws Exception {
            System.out.println("我的callable");
            return "我的返回值";
        }
    }
}
