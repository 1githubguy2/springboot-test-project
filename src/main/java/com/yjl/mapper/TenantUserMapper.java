package com.yjl.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yjl.domain.model.TenantUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author unbroken
 * @Description 用户
 * @Version 1.0
 * @date 2024/9/1 0:58
 */
@Repository
public interface TenantUserMapper {
    IPage<TenantUser> pageList(IPage<TenantUser> page, @Param("params") TenantUser params);
    void updateByCondition(@Param("entity") TenantUser entity);
}
