package com.yjl.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yjl.domain.model.TenantUser;
import com.yjl.service.facade.TenantUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author unbroken
 * @Description 用户
 * @Version 1.0
 * @date 2024/09/01 01:51
 */
@RestController
@RequestMapping("/api/tenantUser")
public class TenantUserController {

    @Autowired
    private TenantUserService tenantUserService;
    @PostMapping("/pageList/{pageNum}/{pageSize}")
    public IPage<TenantUser> pageList(@PathVariable("pageNum") Integer pageNum,
                         @PathVariable("pageSize") Integer pageSize,
                         @RequestBody TenantUser condition) {
        IPage<TenantUser> tenantUserIPage = tenantUserService.pageList(pageNum, pageSize, condition);
        return tenantUserIPage;
    }
}
