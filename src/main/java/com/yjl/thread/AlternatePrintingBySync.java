package com.yjl.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author unbroken
 * @Description 多线程交替打印（sync+wait+notify+CountDownLatch实现）
 * @Version 1.0
 * @date 2023/4/15 22:57
 */
public class AlternatePrintingBySync {
    private static CountDownLatch latch = new CountDownLatch(1);
    public static void main(String[] args) {
        Object obj = new Object();
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        Thread t1 = new Thread(() -> {
            synchronized (obj) {
                for(char ch : aI) {
                    System.out.print(ch);
                    //latch减为0
                    latch.countDown();
                    try {
                        obj.notify();
                        obj.wait();//调用wait方法，释放锁，且当前线程进入waiting状态
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                obj.notify();//必须，否则无法停止程序
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            try {
                //保证t1先执行
                latch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (obj) {
                for(char ch : aC) {
                    System.out.print(ch);
                    try {
                        obj.notify();
                        obj.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                obj.notify();
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
