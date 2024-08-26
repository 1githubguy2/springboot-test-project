package com.yjl.thread;

/**
 * @author unbroken
 * @Description 哲学家用餐问题
 * @Version 1.0
 * @date 2023/4/15 21:48
 */
public class DeadLock {

    public static void main(String[] args) {
        ChopStick cs0 = new ChopStick();
        ChopStick cs1 = new ChopStick();
        ChopStick cs2 = new ChopStick();
        ChopStick cs3 = new ChopStick();
        ChopStick cs4 = new ChopStick();
        Philosohper p0 = new Philosohper(cs0, cs1, 0, "p0");
        Philosohper p1 = new Philosohper(cs1, cs2, 1, "p1");
        Philosohper p2 = new Philosohper(cs2, cs3, 2, "p2");
        Philosohper p3 = new Philosohper(cs3, cs4, 3, "p3");
        Philosohper p4 = new Philosohper(cs4, cs0, 4, "p4");
        p0.start();
        p1.start();
        p2.start();
        p3.start();
        p4.start();
    }

    private static class Philosohper extends Thread{
        private ChopStick left;
        private ChopStick right;
        private int index;
        private String name;

        public Philosohper(ChopStick left, ChopStick right, int index, String name) {
            this.left = left;
            this.right = right;
            this.index = index;
            this.name = name;
        }

        @Override
        public void run() {
            if(index % 2 == 0) {
                synchronized (left) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    synchronized (right) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println(index + "吃完了");
                    }
                }
            }else {
                synchronized (right) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    synchronized (left) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println(index + "吃完了");
                    }
                }
            }

        }
    }
    private static class ChopStick {

    }
}
