package com.yjl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author unbroken
 * @Description TODO
 * @Version 1.0
 * @date 2024/4/1 21:24
 */
@Configuration
@EnableAsync
public class ExecutorConfig implements AsyncConfigurer {
    private final RejectedExecutionHandler rejectedExecutionHandler;
    private final ThreadPoolTaskExecutor taskExecutor;

    public ExecutorConfig() {
        this.rejectedExecutionHandler = new ThreadPoolExecutor.CallerRunsPolicy();
        this.taskExecutor = initAsyncExecutor();
    }

    @Bean("myAsyncExecutor")
    public ThreadPoolTaskExecutor asyncExecutor() {
        return this.taskExecutor;
    }

    private ThreadPoolTaskExecutor initAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setThreadNamePrefix("my-test-prefix-");
        taskExecutor.setCorePoolSize(2);
        taskExecutor.setMaxPoolSize(2);
        taskExecutor.setQueueCapacity(10000);
        taskExecutor.setRejectedExecutionHandler(rejectedExecutionHandler);
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10); // 设置核心线程数
        executor.setMaxPoolSize(20); // 设置最大线程数
        executor.setQueueCapacity(100); // 设置队列容量
        executor.setThreadNamePrefix("CustomExecutor-"); // 设置线程名前缀
        executor.initialize();
        return executor;
    }
}
