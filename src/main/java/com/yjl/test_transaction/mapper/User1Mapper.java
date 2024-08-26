package com.yjl.test_transaction.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yjl.test_transaction.po.User1;

public interface User1Mapper extends BaseMapper<User1> {
//    int deleteByPrimaryKey(Integer id);
//
//    int insertSelective(User1 record);
//
//    User1 selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(User1 record);
//
//    int updateByPrimaryKey(User1 record);
//
    int truncated();
}