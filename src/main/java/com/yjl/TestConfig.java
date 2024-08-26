package com.yjl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author unbroken
 * @Description TODO
 * @Version 1.0
 * @date 2023/4/12 20:42
 */
@Configuration
public class TestConfig {
    @Bean
    public String name() {
        return "Hello Spring.";
    }
}
