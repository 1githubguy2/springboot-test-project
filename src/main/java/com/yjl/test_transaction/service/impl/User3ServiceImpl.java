package com.yjl.test_transaction.service.impl;

import com.yjl.test_transaction.mapper.User1Mapper;
import com.yjl.test_transaction.po.User1;
import com.yjl.test_transaction.service.User3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author unbroken
 * @Description TODO
 * @Version 1.0
 * @date 2023/4/26 12:23
 */
@Service
public class User3ServiceImpl implements User3Service {
    @Autowired
    private User1Mapper user1Mapper;
    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void addUser(User1 user1) {
        user1Mapper.insert(user1);
//        int i = 1 / 0;
        throw new RuntimeException();
    }
}
