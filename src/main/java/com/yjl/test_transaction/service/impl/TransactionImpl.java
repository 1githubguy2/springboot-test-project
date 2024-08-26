package com.yjl.test_transaction.service.impl;

import com.yjl.test_transaction.mapper.User1Mapper;
import com.yjl.test_transaction.po.User1;
import com.yjl.test_transaction.po.User2;
import com.yjl.test_transaction.service.User3Service;
import com.yjl.test_transaction.service.User4Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.ws.Action;

/**
 * @author unbroken
 * @Description TODO
 * @Version 1.0
 * @date 2023/4/26 12:25
 */
@Service
public class TransactionImpl {
    @Autowired
    private User3Service user3Service;

    @Autowired
    private User4Service user4Service;
    @Autowired
    private User1Mapper user1Mapper;
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addUser() {
        User1 user1 = new User1();
        user1.setName("111");
        try {
            user3Service.addUser(user1);
        } catch (RuntimeException e) {
            System.out.println(e);
        }
        User2 user2 = new User2();
        user2.setName("222");
        user4Service.addUser(user2);
//        int i = 1 / 0;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void addUserAlone() {
        User1 user1 = new User1();
        user1.setName("333");
        user1Mapper.insert(user1);
    }
}
