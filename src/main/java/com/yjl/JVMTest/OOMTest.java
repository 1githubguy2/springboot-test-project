package com.yjl.JVMTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author unbroken
 * @Description 测试没有及时释放内存导致OOM问题(上家公司遇到的问题)
 * 配置JVM启动参数指定JVM内存大小：-Xms200m -Xmx200m -XX:+HeapDumpOnOutOfMemoryError
 * 文件读取的参考文章：
 * https://blog.csdn.net/Lzy410992/article/details/109587749#:~:text=%E7%AE%80%E5%8D%95%E7%9A%84%E5%B1%95%E7%A4%BAJava,%E6%96%87%E4%BB%B6%E8%BE%93%E5%85%A5%E8%BE%93%E5%87%BA%E6%B5%81File%E8%AF%BB%E5%8F%96%E5%86%99%E5%85%A5%E6%96%87%E4%BB%B6%E7%9A%84%E5%BF%AB%E9%80%9F%E5%AE%9E%E7%8E%B0%EF%BC%8C%E6%96%B9%E4%BE%BF%E5%A4%A7%E5%AE%B6%E8%AE%B0%E5%BF%86%E4%B8%8E%E5%BF%AB%E9%80%9F%E4%BD%BF%E7%94%A8%E3%80%82
 * @Version 1.0
 * @date 2023/4/24 19:19
 */
public class OOMTest {
    public static void main(String[] args) {
        // OOM情况
//        handleFileWithOOM();
        // 处理上面OOM情况
        handleFileWithoutOOM();
    }

    public static void handleFileWithOOM() {
        HashMap<Integer, String> outterMap = new HashMap<>();
        outterMap.put(0, "1");
        int size = 200;
        for (int i = 1; i < size; i++) {
            outterMap.put(i, readFile());
            handleData(outterMap, i);
        }
    }

    public static void handleFileWithoutOOM() {
        int size = 200;
        for (int i = 1; i < size; i++) {
            handleData(i, readFile());
        }
    }

    public static void handleData(Map<Integer, String> map, int i) {
        System.out.println("当前执行次数:" + i + "----" + map.get(0));
    }

    public static void handleData(int i, String str) {
        HashMap<Integer, String> innerMap = new HashMap<>();
        innerMap.put(0, "1");
        innerMap.put(i, str);
        System.out.println("当前执行次数:" + i + "----" + innerMap.get(0));
    }

    public static String readFile() {
        //E:\temp\test.docx
        StringBuilder sb = new StringBuilder();
        try {
            FileReader reader = new FileReader(new File("E:\\temp\\test.docx"));
            BufferedReader buffReader = new BufferedReader(reader);
            String s;
            while ((s = buffReader.readLine()) != null) {
                sb.append(s);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }
}
