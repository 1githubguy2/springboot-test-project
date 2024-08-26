package com.yjl.thread;

/**
 * @author unbroken
 * @Description TODO
 * @Version 1.0
 * @date 2023/5/14 21:48
 */
public class ThreadLocalTest {
    public static void main(String[] args) {
        ThreadLocal<Long> tl = new ThreadLocal<>();
        tl.set(System.currentTimeMillis());
        Long start = tl.get();
        Long costTime = System.currentTimeMillis() - start;
        tl.remove();
    }
}
