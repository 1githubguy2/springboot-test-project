package com.yjl.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author unbroken
 * @Description TODO
 * @Version 1.0
 * @date 2024/2/11 23:53
 */
@Getter
@RequiredArgsConstructor
public enum OperationType {

    ADD("add"),
    UPDATE("update");
    private String value;
    OperationType(String value) {
        this.value = value;
    }
}
