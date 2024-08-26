package com.yjl.utils;

import java.util.concurrent.TimeUnit;

/**
 * @author unbroken
 * @Description TODO
 * @Version 1.0
 * @date 2023/4/16 11:34
 */
public class SleepUtils {
    public static final void second(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
