package com.yjl.lock;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author unbroken
 * @Description TODO
 * @Version 1.0
 * @date 2023/4/15 13:57
 */
public class JustTest {
    private static class T{
        int m;
        long p;
        boolean b;
        String s = "123234234";
    }
    public static void main(String[] args) {
        T t = new T();
        System.out.println(ClassLayout.parseInstance(t).toPrintable());
    }

    public static void test() {
        StringBuilder sb = new StringBuilder();
        sb.append("1");
        sb.append("2");
        System.out.println(sb);
    }
}
