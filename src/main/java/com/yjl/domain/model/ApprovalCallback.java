package com.yjl.domain.model;

import lombok.Data;

/**
 * @author unbroken
 * @Description TODO
 * @Version 1.0
 * @date 2024/2/11 23:38
 */
@Data
public class ApprovalCallback extends BaseEntity{
    private String username;
    private String address;
    private String type;
    private String status;
}
