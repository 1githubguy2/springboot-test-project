package com.yjl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
@MapperScan("com.yjl.mapper*")
public class SpringbootTestProjectApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(SpringbootTestProjectApplication.class, args);
//        ConfigurableApplicationContext context = SpringApplication.run(SpringbootTestProjectApplication.class, args);
//        System.out.println(context);
//        TeacherService teacherServiceImpl = context.getBean("teacherServiceImpl", TeacherService.class);
//        System.out.println(teacherServiceImpl);
//        teacherServiceImpl.upload();
//        teacherServiceImpl.insertTeacher();
//        StudentService studentServiceImpl = context.getBean("studentServiceImpl", StudentService.class);
//        System.out.println(studentServiceImpl);
//        AnnotationConfigApplicationContext acx = new AnnotationConfigApplicationContext(TestConfig.class);
//        Object name = acx.getBean("a");
//        System.out.println(name);
//        AbstractApplicationContext acx = new ClassPathXmlApplicationContext("beans.xml");
//        Person person = (Person) context.getBean("person");
//        System.out.println(person);
//        acx.close();
    }

}
