package com.yjl.JavaSETest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author unbroken
 * @Description TODO
 * @Version 1.0
 * @date 2023/5/5 20:57
 */
public class CopyOnWriteArrayListTest {
    public static void main(String[] args) {
//        List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c", "d"));
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>(Arrays.asList("a", "b", "c", "d"));
        Iterator<String> iterator = list.iterator();
        new Thread(() -> {
            System.out.println("开始执行查询");
            while (iterator.hasNext()) {
                String str = iterator.next();
                System.out.println(str);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("结束执行查询");
        }).start();

        new Thread(() -> {
            System.out.println("开始执行插入");
            list.add("e");
            list.remove("a");
            System.out.println("结束执行插入");
        }).start();
    }
}
