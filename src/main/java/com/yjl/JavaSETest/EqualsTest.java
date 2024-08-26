package com.yjl.JavaSETest;

/**
 * @author unbroken
 * @Description TODO
 * @Version 1.0
 * @date 2023/4/15 9:38
 */
public class EqualsTest {
    public static void main(String[] args) {
        //看字节码信息可以发现，这里会进行自动装箱
        Integer i1 = 3;
        Integer i2 = 3;
        //查看源码发现[-128,127]范围的Integer对象会存储在方法区的整数型常量池中进行对象复用
        System.out.println(i1 == i2);//true
        Integer i3 = 200;
        Integer i4 = 200;
        System.out.println(i3 == i4);//false
        //对于浮点型的Double和Float，不会做缓存对象操作，装箱的时候是直接new一个新的对象
        Double d5 = 3.0;
        Double d6 = 3.0;
        System.out.println(d5 == d6);//false
//        Double i = 2.0;
//        Double j = 2.0;
//        Float n = 2.0f;
//        Integer m = 2;
//        System.out.println(i == j);
//        int i1 = i.hashCode();
//        int i2 = j.hashCode();
//        int i3 = m.hashCode();
//        int i4 = n.hashCode();
//        System.out.println(i.equals(j));
//        System.out.println(i1 == i2);
//        System.out.println(i1 == i3);
//        System.out.println(i1 == i4);
//        Double i = 2.0;
//        Double j = 2.0;
//        System.out.println(i.equals(j));
//        HashSet<String> set = new HashSet<>();
//        String a = "1";
//        set.add("111");
//        HashMap<String, String> map = new HashMap<>();
//        map.put("1","11");
//        map.get("1");
    }
}
