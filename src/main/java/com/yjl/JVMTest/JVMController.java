package com.yjl.JVMTest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author unbroken
 * @Description TODO
 * @Version 1.0
 * @date 2023/5/3 13:50
 */
@RestController
@RequestMapping("/jvm")
public class JVMController {

    @GetMapping("/handleFileWithOOM")
    public void handleFileWithOOM() {
        HashMap<Integer, String> outterMap = new HashMap<>();
        outterMap.put(0, "1");
        int size = 200;
        for (int i = 1; i < size; i++) {
            outterMap.put(i, readFile());
            handleData(outterMap, i);
        }
    }

    @GetMapping("/handleFileWithoutOOM")
    public void handleFileWithoutOOM() {
        int size = 200;
        for (int i = 1; i < size; i++) {
            handleData(i, readFile());
        }
    }

    public void handleData(Map<Integer, String> map, int i) {
        System.out.println("当前执行次数:" + i + "----" + map.get(0));
    }

    public void handleData(int i, String str) {
        HashMap<Integer, String> innerMap = new HashMap<>();
        innerMap.put(0, "1");
        innerMap.put(i, str);
        System.out.println("当前执行次数:" + i + "----" + innerMap.get(0));
    }

    public String readFile() {
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
