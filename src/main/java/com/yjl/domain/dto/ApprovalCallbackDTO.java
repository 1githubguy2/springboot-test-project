package com.yjl.domain.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ApprovalCallbackDTO implements Serializable {

    @ApiModelProperty(value = "主键(新增提交数据可以忽略)", hidden = true)
    private String id;
    private String username;
    private String address;
    private String type;
    private String status;

}
