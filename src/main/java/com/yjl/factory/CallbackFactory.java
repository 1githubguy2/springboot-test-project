package com.yjl.factory;

import com.yjl.enums.ApprovalCallbackType;
import com.yjl.service.facade.CallbackService;
import com.yjl.service.impl.SuperviseInnerApprovalCallbackImpl;
import com.yjl.service.impl.TaskClosureApprovalCallbackImpl;
import com.yjl.service.impl.TaskDelayApprovalCallbackImpl;
import com.yjl.service.impl.TaskSummaryApprovalCallbackImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author unbroken
 * @Description TODO
 * @Version 1.0
 * @date 2024/2/24 9:28
 */
@RequiredArgsConstructor
@Component
public class CallbackFactory {

    private final SuperviseInnerApprovalCallbackImpl superviseInnerApprovalCallbackImpl;
    private final TaskDelayApprovalCallbackImpl taskDelayApprovalCallbackImpl;
    private final TaskClosureApprovalCallbackImpl taskClosureApprovalCallbackImpl;
    private final TaskSummaryApprovalCallbackImpl taskSummaryApprovalCallbackImpl;

    public CallbackService createService(String type) {
        if (ApprovalCallbackType.superviseInnerApproval.name().equals(type)) {
            return superviseInnerApprovalCallbackImpl;
        } else if (ApprovalCallbackType.taskDelayApproval.name().equals(type)) {
            return taskDelayApprovalCallbackImpl;
        } else if (ApprovalCallbackType.taskClosureApproval.name().equals(type)) {
            return taskClosureApprovalCallbackImpl;
        } else if (ApprovalCallbackType.taskSummaryApproval.name().equals(type)) {
            return taskSummaryApprovalCallbackImpl;
        }
        throw new RuntimeException("没有对应回调处理");
    }
}
