package com.yjl.thread;

/**
 * @author unbroken
 * @Description Java并发编程103页 join()测试
 * 如果一个线程A执行了thread.join()语句，其含义是：当前线程A等待thread线程终止之后才从thread.join()返回;
 * 应用场景：按顺序打印A,B,C
 * @Version 1.0
 * @date 2023/4/15 10:38
 */
public class Join {
        private static final String[] strArr = {"A", "B", "C"};
//    private static final String[] strArr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    public static void main(String[] args) {
        Thread previous = Thread.currentThread();
        for (int i = 1; i < strArr.length; i++) {
            Thread thread = new Thread(new Domino(previous), strArr[i]);
            thread.start();
            previous = thread;
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(strArr[0]);
    }

    static class Domino implements Runnable {

        private final Thread thread;

        public Domino(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName());
        }
    }
}
