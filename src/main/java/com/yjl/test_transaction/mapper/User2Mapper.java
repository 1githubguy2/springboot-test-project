package com.yjl.test_transaction.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yjl.test_transaction.po.User2;

public interface User2Mapper extends BaseMapper<User2> {
//    int deleteByPrimaryKey(Integer id);
//
//    int insertSelective(User2 record);
//
//    User2 selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(User2 record);
//
//    int updateByPrimaryKey(User2 record);
    
    int truncated();
}