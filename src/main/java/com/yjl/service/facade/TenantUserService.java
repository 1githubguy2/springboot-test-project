package com.yjl.service.facade;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yjl.domain.model.TenantUser;

/**
 * @author unbroken
 * @Description 用户
 * @Version 1.0
 * @date 2024/9/1 1:24
 */
public interface TenantUserService {
    IPage<TenantUser> pageList(int pageNum, int pageSize, TenantUser condition);
}
