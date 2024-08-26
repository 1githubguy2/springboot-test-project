package com.yjl.thread;

import java.util.concurrent.*;

/**
 * @author unbroken
 * @Description TODO
 * @Version 1.0
 * @date 2023/5/20 13:19
 */
public class ConcurrentToolTest {
    public static void main(String[] args) {
        testExchanger();
    }
    public static void testSemaphore() {
        ExecutorService pool = Executors.newFixedThreadPool(30);
        Semaphore semaphore = new Semaphore(10);
        for(int i = 0; i < 30; i++) {
            pool.execute(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("save data:" + semaphore.availablePermits());
                    semaphore.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        pool.shutdown();
    }
    public static void testCyclicBarrier() {
//        CyclicBarrier barrier = new CyclicBarrier(2);
        CyclicBarrier barrier = new CyclicBarrier(2, () -> {
            System.out.println("my first");
        });
        new Thread(() -> {
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
            System.out.println("test barrierThread");
        }, "barrierThread").start();
        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
        System.out.println("main end");
    }
    public static void testCountDownLatch() {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(() -> {
            System.out.println("testCountDownLatch");
            countDownLatch.countDown();
        }, "t1");
        try {
            countDownLatch.await(30, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void testExchanger() {
        Exchanger<String> exchanger = new Exchanger<>();
        ExecutorService pool = Executors.newFixedThreadPool(2);
        pool.execute(() -> {
            try {
                String a = "银行流水a";
                String b = exchanger.exchange(a);
                System.out.println("线程1: " + a.equals(b) + ";当前的a为: " + a + ";当前的b为: " + b);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        pool.execute(() -> {
            try {
                String b = "银行流水b";
                Thread.sleep(5000);
                String a = exchanger.exchange(b);
                System.out.println("线程2: " + a.equals(b) + ";当前的a为: " + a + ";当前的b为: " + b);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        pool.shutdown();
    }
}
