package com.yjl.service.facade;

import com.yjl.domain.model.ApprovalCallback;

/**
 * @author unbroken
 * @Description TODO
 * @Version 1.0
 * @date 2024/2/24 9:22
 */
public interface CallbackService {
    void create(ApprovalCallback callback);

    void refuse(ApprovalCallback callback);

    void complete(ApprovalCallback callback);
}
