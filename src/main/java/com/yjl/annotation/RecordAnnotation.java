package com.yjl.annotation;

import com.yjl.domain.enums.OperationType;

import java.lang.annotation.*;

/**
 * @author unbroken
 * @Description TODO
 * @Version 1.0
 * @date 2024/2/11 23:50
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.PARAMETER})
@Documented
public @interface RecordAnnotation {
    OperationType operationType();
}
