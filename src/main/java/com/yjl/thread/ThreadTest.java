package com.yjl.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author unbroken
 * @Description TODO
 * @Version 1.0
 * @date 2023/4/14 17:26
 */
public class ThreadTest {

    public static void main(String[] args) {

    }
    public static void testInterrupt() {
        Thread thread = new Thread(() -> {
            for(int i = 0; i < 1000000; i++) {
                System.out.println(i);
                if(Thread.currentThread().isInterrupted() && i > 150000) {
                    break;
                }
            }
        });
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        thread.interrupt();
        System.out.println("end");
        for(int i = 0; i < 10; i++) {
            System.out.println("main----"+i);
        }
    }

    public static void testDaemon() {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                System.out.println("finally run");
            }
//            System.out.println("run");
        });
        thread.setDaemon(true);
        thread.start();
    }
    public static void testExtendsThead() {
        ThreadThread thread = new ThreadThread();
        thread.start();
    }
    public static void testRunnable() {
        RunnableThread runnable = new RunnableThread();
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public static void testCallable() {
        Callable<String> callable = new CallableThead();
        FutureTask<String> task1 = new FutureTask<>(callable);
        Thread thread = new Thread(task1);
        thread.start();
        try {
            System.out.println(task1.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
class ThreadThread extends Thread{
    @Override
    public void run() {
        System.out.println("extends thread");
    }
}
class RunnableThread implements Runnable{

    @Override
    public void run() {
        System.out.println("Runnable");
    }
}
class CallableThead implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("callable");
        return "返回值";
    }
}
