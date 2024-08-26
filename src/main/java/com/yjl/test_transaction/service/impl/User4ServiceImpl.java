package com.yjl.test_transaction.service.impl;

import com.yjl.test_transaction.mapper.User2Mapper;
import com.yjl.test_transaction.po.User2;
import com.yjl.test_transaction.service.User4Service;
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
public class User4ServiceImpl implements User4Service {
    @Autowired
    private User2Mapper user2Mapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public void addUser(User2 user2) {
        user2Mapper.insert(user2);
//        int i = 1 / 0;
    }
}
