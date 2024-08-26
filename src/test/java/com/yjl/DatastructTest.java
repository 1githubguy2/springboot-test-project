package com.yjl;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author unbroken
 * @Description TODO
 * @Version 1.0
 * @date 2023/4/9 11:07
 */
@SpringBootTest
public class DatastructTest {

    @Test
    public void testList() {
//        List<Integer> list = Arrays.asList(5,6,7,8);
//        list.remove(0);
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(6);
        list.add(6);
        list.add(6);
        list.add(7);
        list.add(8);
        System.out.println(list);
//        list.remove(6);
//        list.remove(new Integer(6));
//        for(int i = 0; i < list.size(); i++) {
//            if(list.get(i) == 6) {
//                list.remove(i);
//                i--;
//            }
//        }
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()) {
            int item = iterator.next();
            if(item == 6) {
//                list.remove(item);
                iterator.remove();
            }
        }
        System.out.println(list);
    }

    @Test
    public void testReentrantLock() {
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
    }

    @Test
    public void testThreadLocal() {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("111");
        threadLocal.remove();
    }

}
