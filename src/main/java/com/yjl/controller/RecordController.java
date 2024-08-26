package com.yjl.controller;

import com.yjl.annotation.RecordAnnotation;
import com.yjl.domain.model.User;
import com.yjl.domain.enums.OperationType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author unbroken
 * @Description AOP和自定义注解
 * @Version 1.0
 * @date 2024/2/11 23:36
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/record")
public class RecordController {

    @RecordAnnotation(operationType = OperationType.ADD)
    @PostMapping
    public void create(@RequestBody User user) {
        System.out.println("记录日志");

    }
    @GetMapping("test")
    public String test() {
        System.out.println("记录日志");
        return "test";
    }

}
