package com.yjl;

import com.yjl.service.facade.TeacherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootTest {

    @Autowired
    private TeacherService teacherServiceImpl;
    @Test
    void contextLoads() {
        teacherServiceImpl.insertTeacher();
    }

}
