package com.yjl.test_transaction.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user2")
public class User2 {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String name;
}