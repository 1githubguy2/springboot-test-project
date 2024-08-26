package com.yjl.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author unbroken
 * @Description 多线程交替打印（ReentrantLock+Condition实现）
 * @Version 1.0
 * @date 2023/4/15 23:23
 */
public class AlternatePrintingByReentrantLock {
    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();
        ReentrantLock lock = new ReentrantLock();
        Condition conditionT1 = lock.newCondition();//队列
        Condition conditionT2 = lock.newCondition();

        CountDownLatch latch = new CountDownLatch(1);

        new Thread(() -> {
            lock.lock();
            try {
                for (char ch : aI) {
                    System.out.print(ch);
                    latch.countDown();
                    conditionT2.signal();//相当于notify()
                    conditionT1.await();//相当于wait()
                }
                conditionT2.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            try {
                latch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            lock.lock();
            try {
                for (char ch : aC) {
                    System.out.print(ch);
                    conditionT1.signal();
                    conditionT2.await();
                }
                conditionT1.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }
}
