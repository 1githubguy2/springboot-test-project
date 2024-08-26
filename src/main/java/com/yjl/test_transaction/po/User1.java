package com.yjl.test_transaction.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user1")
public class User1 {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

}