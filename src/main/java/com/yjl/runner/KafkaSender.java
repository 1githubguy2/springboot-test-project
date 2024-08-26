package com.yjl.runner;

import com.alibaba.fastjson.JSONObject;
import com.yjl.domain.dto.KafkaPicDTO;
import com.yjl.utils.KafkaUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author unbroken
 * @Description TODO
 * @Version 1.0
 * @date 2024/4/23 23:08
 */
@Slf4j
@RequiredArgsConstructor
@Configuration
@EnableScheduling
//@ConditionalOnProperty(name = "kafka.enable", havingValue = "true")
public class KafkaSender {
    private final ThreadPoolTaskExecutor taskExecutor;
//    private final KafkaUtil kafkaUtil;

    @Value("${kafka.topic.pic:tp_pic}")
    private String tp_pic;
    @Value("${kafka.topic.log:tp_log}")
    private String tp_log;
//    @Scheduled(cron = "7 * * * * ?")
//    @Scheduled(cron = "*/2 * * * * ?")
    public void send() {
        List<Future<?>> futures = new ArrayList<>();
        //异步发送
        futures.add(taskExecutor.submit(this::sendPic));
        futures.add(taskExecutor.submit(this::sendLog));
        for (Future<?> future : futures) {
            try {
                //阻塞等待，防止主线程提前结束，导致子线程没结束的情况下，定时任务新启动一个任务，最终会导致图片重复发送
                Object o = future.get();
                System.out.println(o);
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void sendPic() {
        System.out.println("同步图片线程1开始：" + Thread.currentThread().getName());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("同步图片线程1结束：" + Thread.currentThread().getName());
//        return "发送图片结束";
//        KafkaPicDTO kafkaPicDTO = new KafkaPicDTO();
//        kafkaUtil.send(tp_pic, JSONObject.toJSONString(kafkaPicDTO));
    }
    public void sendLog() {
        System.out.println("同步日志线程2开始：" + Thread.currentThread().getName());
        System.out.println("同步日志线程2结束：" + Thread.currentThread().getName());
//        return "发送日志结束";
//        kafkaUtil.send("log_topic", "log_value");
    }
}
