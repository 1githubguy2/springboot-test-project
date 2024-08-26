package com.yjl.lock;

import java.util.HashMap;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

/**
 * @author unbroken
 * @Description TODO
 * @Version 1.0
 * @date 2023/4/16 12:03
 */
public class
SynchronizedTest {
    private static final Object obj = new Object();
    public static void main(String[] args) {
//        HashMap<String, String> stringStringHashMap = new HashMap<>();
//        stringStringHashMap.put("1", "1");
        int i = myTest(5, 1);
        System.out.println(i);
    }

    public static int myTest(int n, int start) {
        if(n == 1) {
            return start;
        }
        int i = 1, cur = start, originN = n;
        while(i <= originN) {
            if(cur % 3 == 0 || contains(cur)) {
                n--;
            }
            cur++;
            i++;
        }
        return myTest(n, cur);
    }
    public static boolean contains(int num) {
        while(num != 0) {
            int tail = num % 10;
            if(tail == 3) {
                return true;
            }
            num /= 10;
        }
        return false;
    }
    public static String test(String aStr, String bStr) {
        StringBuilder sb = new StringBuilder("");
        int tag = 0;
        int aLen = aStr.length(), bLen = bStr.length();
        int tailA = aLen - 1, tailB = bLen - 1;
        while(tailA >= 0 && tailB >= 0) {
            int a = aStr.charAt(tailA) - '0';
            int b = bStr.charAt(tailB) - '0';
            int cur = a + b + tag;
            tag = cur / 10;
            int res = cur % 10;
            sb.append(res);
            tailA--;
            tailB--;
        }
        if(tailA == -1 && tailB == -1 && tag == 1) {
            sb.append(1);
        }else if(tailB == -1) {
            while(tag == 1 && tailA >= 0) {
                int res = aStr.charAt(tailA) - '0' + tag;
                sb.append(res % 10);
                tag = res / 10;
                tailA--;
            }
            StringBuilder curS = new StringBuilder(aStr.substring(0, tailA));
            sb.append(curS.reverse());
        }else {
            while(tag == 1 && tailB >= 0) {
                int res = bStr.charAt(tailB) - '0' + tag;
                sb.append(res % 10);
                tag = res / 10;
                tailB--;
            }
            StringBuilder curS = new StringBuilder(bStr.substring(0, tailB));
            sb.append(curS.reverse());
        }
        return sb.reverse().toString();
    }
    public static void testLockSupport() {
        Thread lockThread = new Thread(() -> {
            System.out.println("before lockSupport");
            LockSupport.park();
            System.out.println("after lockSupport");
        }, "lockThread");
        lockThread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("before end");
        LockSupport.unpark(lockThread);
        System.out.println("main");
    }
    public static void testLock() {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        lock.lock();
        lock.tryLock();
    }

    public static void testSynchronized() {
        synchronized (obj) {
            System.out.println("synchronized test");
        }
    }
}
