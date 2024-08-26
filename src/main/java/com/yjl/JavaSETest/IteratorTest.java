package com.yjl.JavaSETest;

import org.apache.logging.log4j.spi.CopyOnWrite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author unbroken
 * @Description TODO
 * @Version 1.0
 * @date 2023/5/5 13:36
 */
public class IteratorTest {
    public static void main(String[] args) {
        Integer[] arr = {2, 4, 7, 3, 8};
        List<Integer> arrList = Arrays.asList(arr);
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(2);
        list.add(7);
        list.add(7);
        list.add(8);
        list.add(7);
        list.add(7);
        list.add(10);
        CopyOnWriteArrayList<Integer> copyOnWrite = new CopyOnWriteArrayList<>();
        copyOnWrite.add(5);
        System.out.println(copyOnWrite);
//        Iterator<Integer> iterator1 = arrList.iterator();
        Iterator<Integer> iterator2 = list.iterator();
//        while(iterator1.hasNext()) {
//            Integer item = iterator1.next();
//            System.out.print(item + " ");
//            if(item == 2) {
//                iterator1.remove();
//            }
//        }
//        System.out.println(arrList);
//        for(int i = 0; i < list.size(); i++) {
//            Integer item = list.get(i);
//            if(item == 7) {
//                list.remove(item);
//            }
//        }
//        for(Integer item : list) {
//            if(item == 7) {
//                list.remove(item);
//            }
//        }
        while(iterator2.hasNext()) {
            Integer item = iterator2.next();
            if(item == 7) {
                list.remove(item);
//                iterator2.remove();
            }
        }
        System.out.println(list);
    }

}
