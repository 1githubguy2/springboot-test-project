package com.yjl.JavaSETest;

import com.yjl.JavaSETest.Data;

import java.util.HashSet;

/**
 * @author unbroken
 * @Description TODO
 * @Version 1.0
 * @date 2023/4/14 22:15
 */
public class Test02 {
    public static void main(String[] args) {
        Data data1 = new Data(1,2);
        Data data2 = new Data(1,2);
//        Data data3 = new Data(2,4);
        HashSet<Data> set = new HashSet<>();
        set.add(data1);
        set.add(data2);
//        set.add(data3);
        for (Data data : set) {
            System.out.println(data);
        }
    }
}
