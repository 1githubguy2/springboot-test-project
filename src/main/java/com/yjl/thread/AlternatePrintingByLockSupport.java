package com.yjl.thread;

import java.util.concurrent.locks.LockSupport;

/**
 * @author unbroken
 * @Description 多线程交替打印（LockSupport实现）
 * @Version 1.0
 * @date 2023/4/15 22:46
 */
public class AlternatePrintingByLockSupport {
    private static Thread t1;
    private static Thread t2;

    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        t1 = new Thread(() -> {
            for (char ch : aI) {
                System.out.print(ch);
                LockSupport.unpark(t2);//叫醒t2
                LockSupport.park();//t1阻塞，当前线程阻塞
            }
        }, "t1");

        t2 = new Thread(() -> {
            for (char ch : aC) {
                LockSupport.park();//t2挂起
                System.out.print(ch);
                LockSupport.unpark(t1);//叫醒t1
            }
        }, "t2");
        t1.start();
        t2.start();
    }
}
