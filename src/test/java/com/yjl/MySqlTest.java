package com.yjl;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author unbroken
 * @Description 测试插入100万数据到数据库，最终方案：多线程+原生jdbc：
 * 开启多线程，10个，每个线程插入10万条数据，分两层循环，内层每1万条数据提交一次插入操作
 * @Version 1.0
 * @date 2023/5/6 15:36
 */
@SpringBootTest
public class MySqlTest {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;


    @Test
    public void testInsert() {
        long start = System.currentTimeMillis();
        String sql = "INSERT INTO tbl_dynamic_test(dynamic_title, content, dynamic_img, create_time, uid) VALUES(?, ?, ?, ?, ?);";
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            conn.setAutoCommit(false);
            PreparedStatement pst = conn.prepareStatement(sql);
            for(int i = 0; i < 100; i++) {
                //耗时6分钟
                for(int j = 0; j < 10000; j++) {
                    pst.setString(1, "动态标题" + j);
                    pst.setString(2, "动态内容" + j);
                    pst.setString(3, "../image/user.jpg");
                    pst.setObject(4, LocalDateTime.now());
                    pst.setInt(5, i % 2 == 0 ? 2 : 3);
                    pst.addBatch();
                }
                pst.executeBatch();
                conn.commit();
                //5分钟100万数据
//                pst.setInt(1,  id++);
//                pst.setString(2, "动态标题" + i);
//                pst.setString(3, "动态内容" + i);
//                pst.setString(4, "../image/user.jpg");
//                pst.setObject(5, LocalDateTime.now());
//                pst.setInt(6, i % 2 == 0 ? 2 : 3);
//                pst.addBatch();
            }
//            pst.executeBatch();
//            conn.commit();
            pst.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时:" + (end - start));
    }


    @Test
    public void testInsertWithThread() throws InterruptedException {
        int threadCnt = 10;
        long start = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(threadCnt);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                10,
                10,
                0L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1024),
                new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build(),
                new ThreadPoolExecutor.AbortPolicy()
        );
//        String sql = "insert into user(name, age, address) values(?, ?, ?)";
        String sql = "INSERT INTO tbl_dynamic_test(dynamic_title, content, dynamic_img, create_time, uid) VALUES(?, ?, ?, ?, ?);";
        for(int k = 0; k < threadCnt; k++) {
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName()+"开始执行：");
                try {
                    Connection conn = DriverManager.getConnection(url, username, password);
                    conn.setAutoCommit(false);
                    PreparedStatement pst = conn.prepareStatement(sql);
                    //一次提交10000条分10次提交，130秒（最终方案）
                    for(int i = 0; i < 10; i++) {
                        //一次提交10万条的话，180秒
                        for(int j = 0; j < 10000; j++) {
//                            pst.setString(1, "小明" + j);
//                            pst.setInt(2, j % 100);
//                            pst.setString(3, "广州" + j);
                            pst.setString(1, "动态标题" + j);
                            pst.setString(2, "动态内容" + j);
                            pst.setString(3, "../image/user.jpg");
                            pst.setObject(4, LocalDateTime.now());
                            pst.setInt(5, j % 2 == 0 ? 2 : 3);
                            pst.addBatch();
                        }
                        pst.executeBatch();
                        conn.commit();
                    }
                    pst.close();
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    countDownLatch.countDown();
                    System.out.println(Thread.currentThread().getName() + "：减一，当前count:"+countDownLatch.getCount());
                }
            });
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println("耗时:" + (end - start));
    }
}
