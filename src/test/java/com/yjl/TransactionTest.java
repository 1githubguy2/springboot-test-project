package com.yjl;

import com.yjl.test_transaction.service.impl.TransactionImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author unbroken
 * @Description TODO
 * @Version 1.0
 * @date 2023/4/26 12:26
 */
@SpringBootTest
public class TransactionTest {
    @Autowired
    private TransactionImpl transactionImpl;

    @Test
    public void test() {
        transactionImpl.addUser();
    }

    @Test
    public void testAlone() {
        transactionImpl.addUserAlone();
    }
}
