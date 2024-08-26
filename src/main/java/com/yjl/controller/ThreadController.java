package com.yjl.controller;

import com.yjl.annotation.RecordAnnotation;
import com.yjl.domain.enums.OperationType;
import com.yjl.domain.model.User;
import com.yjl.service.facade.ThreadService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Source;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author unbroken
 * @Description 多线程
 * @Version 1.0
 * @date 2024/2/11 23:36
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/thread")
public class ThreadController {

    private final ThreadService threadService;


    @RecordAnnotation(operationType = OperationType.ADD)
    @PostMapping
    public void create(@RequestBody User user) {
        System.out.println("主线程");
        new MyThread().start();
        MyRunnable myRunnable = new MyRunnable();
        new Thread(myRunnable).start();
        MyCallable myCallable = new MyCallable();
        FutureTask<String> futureTask = new FutureTask<>(myCallable);
        new Thread(futureTask).start();
    }
    @RecordAnnotation(operationType = OperationType.UPDATE)
    @PostMapping("/asyncUpdate")
    public String asyncUpdate(@RequestBody User user) {

        System.out.println("主线程开始，名称为:" + Thread.currentThread().getName());
        threadService.asyncUpdate();
        return "结束";
    }

    public static void main(String[] args) {
        System.out.println("主线程");
        new MyThread().start();
        MyRunnable myRunnable = new MyRunnable();
        new Thread(myRunnable).start();
        MyCallable myCallable = new MyCallable();
        FutureTask<String> futureTask = new FutureTask<>(myCallable);
        new Thread(futureTask).start();
    }
    static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println("extends Thread");
        }
    }
    static class MyRunnable implements Runnable{

        @Override
        public void run() {
            System.out.println("implements Runnable");
        }
    }
    static class MyCallable implements Callable<String>{

        @Override
        public String call() throws Exception {
            System.out.println("implements callable");
            return "callable";
        }
    }


}
