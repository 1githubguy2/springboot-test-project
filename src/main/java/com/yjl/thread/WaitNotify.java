package com.yjl.thread;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author unbroken
 * @Description Java并发编程99页 测试wait，notify，notifyAll
 * 调用wait，该线程从running进入waiting状态，并且释放锁；
 * 调用notifyAll（此时不会释放锁，调用sleep也不会释放锁），所有处于waiting状态的线程进入blocked状态，
 * 线程再次获取到锁之后，才从wait()方法返回，执行之后的逻辑；
 * @Version 1.0
 * @date 2023/4/15 10:19
 */
public class WaitNotify {
    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) {

    }

    public static void testWaitNotify() {
        Thread waitThread = new Thread(() -> {
            //加锁，拥有对象lock的Monitor
            synchronized (lock) {
                //当条件不满足时，继续wait，同时释放了lock的锁
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread()
                                + "flag is true.wait @ "
                                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                        lock.wait();
                        System.out.println("被notify(),并且获取到锁");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                //条件满足时，完成工作
                System.out.println(Thread.currentThread()
                        + "flag is false.running @ "
                        + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }
        }, "WaitThread");
        waitThread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Thread notifyThread = new Thread(() -> {
            //加锁，拥有lock的Monitor
            synchronized (lock) {
                //获取lock的锁，然后进行通知，通知时不会释放lock的锁，
                //直到当前线程释放了lock后，WaitThread才能从wait方法中返回
                System.out.println(Thread.currentThread()
                        + " hold lock.notify @ "
                        + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                lock.notifyAll();
                flag = false;
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                //再次加锁
                synchronized (lock) {
                    System.out.println(Thread.currentThread()
                            + " hold lock again.sleeps @ "
                            + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }, "NotifyThread");
        notifyThread.start();
    }
}
