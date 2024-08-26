package com.yjl.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @author unbroken
 * @Description TODO
 * @Version 1.0
 * @date 2024/4/20 10:55
 */
//@Component
@Slf4j
public class KafkaUtil {
    @Value("${kafka.servers}")
    private String servers;
    @Value("${kafka.user}")
    private String user;
    @Value("${kafka.password}")
    private String password;
    private static final Integer DEFAULT_MAX_REQUEST_SIZE = 1024 * 1024 * 5;
    private volatile KafkaProducer<String, String> producer;

    public void initProducer() {
        if (producer == null) {
            synchronized (KafkaUtil.class) {
                if (producer == null) {
                    Properties prop = new Properties();
                    prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
                    prop.put(ProducerConfig.RETRIES_CONFIG, 5);
                    prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
                    prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
                    prop.put("security.protocol", "SASL_PLAINTEXT");
                    prop.put("sasl.mechanism", "SCRAM-SHA-256");
                    prop.put("sasl.jaas.config", "org.apache.kafka.common.security.scram.ScramLoginModule required username = \"" + user + "\" password=\"" + password + "\";");
                    prop.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, DEFAULT_MAX_REQUEST_SIZE);
                    producer = new KafkaProducer<>(prop);
                }
            }
        }
    }

    public void send(String topic, String value) {
        initProducer();
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, value);
        log.info("发送消息：topic:{}, value:{}", record.key(), record.value());
        try {
            producer.send(record);
        } catch (Exception e) {
            log.error("数据推送失败，原因为：{}", e.getMessage());
            e.printStackTrace();
        }
    }
}
