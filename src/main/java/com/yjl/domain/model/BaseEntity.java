package com.yjl.domain.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author unbroken
 * @Description TODO
 * @Version 1.0
 * @date 2024/2/25 11:24
 */
@Data
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public abstract class BaseEntity implements Serializable {
    @ApiModelProperty(value = "主键(实体id)", hidden = true)
    @Id
    @org.springframework.data.annotation.Id
    @Column(columnDefinition = "varchar(50) comment '主键'")
    private String id;
    @ApiModelProperty(value = "创建时间", hidden = true)
    @Column(columnDefinition = "datetime(6) comment '创建时间'")
    @CreatedDate
    private LocalDateTime createTime;
    @ApiModelProperty(value = "更新时间", hidden = true)
    @Column(columnDefinition = "datetime(6) comment '更新时间'")
    @LastModifiedDate
    private LocalDateTime updateTime;
    @ApiModelProperty(value = "创建人", hidden = true)
    @Column(columnDefinition = "varchar(255) comment '创建人'")
    @CreatedBy
    private String createUser;
    @ApiModelProperty(value = "更新人", hidden = true)
    @Column(columnDefinition = "varchar(255) comment '更新人'")
    @LastModifiedBy
    private String updateUser;
    @ApiModelProperty(value = "软删除，默认不删除", hidden = true)
    @Column(columnDefinition = "varchar(1) comment '软删除'")
    private Boolean deleted = false;
    @ApiModelProperty(value = "租户id", hidden = true)
    @Column(columnDefinition = "varchar(50) comment '租户id'")
    private String tenantId;

}
