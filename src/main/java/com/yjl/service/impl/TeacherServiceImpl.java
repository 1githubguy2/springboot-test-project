package com.yjl.service.impl;

import com.yjl.service.facade.TeacherService;
import com.yjl.service.facade.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author unbroken
 * @Description TODO
 * @Version 1.0
 * @date 2023/4/18 21:22
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService currentProxy;

    @Override
    public void selectTeacher() {
        System.out.println("selectTeacher");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void insertTeacher() {
        studentService.insertStudent();
        System.out.println("insertTeacher");
    }

    @Override
    public void upload() {
        System.out.println("upload");
        this.insertTeacher();
        currentProxy.insertTeacher();
    }
}
