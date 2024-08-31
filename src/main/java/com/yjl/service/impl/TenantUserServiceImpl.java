package com.yjl.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjl.domain.model.TenantUser;
import com.yjl.mapper.TenantUserMapper;
import com.yjl.service.facade.TenantUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author unbroken
 * @Description 用户
 * @Version 1.0
 * @date 2024/9/1 1:25
 */
@Service
public class TenantUserServiceImpl implements TenantUserService {
    @Autowired
    private TenantUserMapper tenantUserMapper;
    @Override
    public IPage<TenantUser> pageList(int pageNum, int pageSize, TenantUser condition) {
        Page<TenantUser> queryPage = new Page<>(pageNum, pageSize);
        IPage<TenantUser> tenantUserIPage = tenantUserMapper.pageList(queryPage, condition);
        return tenantUserIPage;
    }
}
