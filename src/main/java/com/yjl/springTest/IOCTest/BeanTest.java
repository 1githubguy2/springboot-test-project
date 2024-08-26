package com.yjl.springTest.IOCTest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author unbroken
 * @Description TODO
 * @Version 1.0
 * @date 2023/5/9 12:04
 */
@Component
@Slf4j
public class BeanTest implements InitializingBean, ApplicationContextAware {
    private Bean2 bean2;

    @Autowired
    public void setBean2(Bean2 bean2) {
        log.info("@Autowired生效: {}", bean2);
        this.bean2 = bean2;
    }

    @PostConstruct
    public void initMethod() {
        log.info("@PostConstruct生效...............");
    }

    public BeanTest() {
        log.info("构造方法执行..............");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("调用afterPropertiesSet.......");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("调用applicationContext.........");
    }
}
