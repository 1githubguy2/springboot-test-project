package com.yjl.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author unbroken
 * @Description @EqualsAndHashCode(callSuper = true)注解的作用就是将其父类属性也进行比较
 * @Version 1.0
 * @date 2024/8/24 23:25
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sys_tenant_user")
@Data
public class TenantUser extends BaseEntity {

    @Column(columnDefinition = "varchar(255) comment '名称'")
    private String name;
    @Column(columnDefinition = "varchar(255) comment '编码'")
    private String code;

}
