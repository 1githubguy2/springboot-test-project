package com.yjl.service.impl;

import com.yjl.service.facade.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author unbroken
 * @Description TODO
 * @Version 1.0
 * @date 2023/4/18 21:25
 */
@Service
public class StudentServiceImpl implements StudentService {
//    @Autowired
//    private TeacherService teacherServiceImpl;
    @Override
    public void selectStudent() {
        System.out.println("selectStudent");
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, isolation = Isolation.DEFAULT)
    @Override
    public void insertStudent() {
        System.out.println("insertStudent");
    }
}
