package com.yjl.service.impl;

import com.yjl.domain.model.ApprovalCallback;
import com.yjl.enums.ApprovalCallbackStatus;
import com.yjl.enums.ApprovalCallbackType;
import com.yjl.factory.CallbackFactory;
import com.yjl.service.facade.ApprovalService;
import com.yjl.service.facade.CallbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author unbrokene
 * @Description TODO
 * @Version 1.0
 * @date 2023/4/18 21:25
 */
@RequiredArgsConstructor
@Service
public class ApprovalServiceImpl implements ApprovalService {
    private final CallbackFactory callbackFactory;
    private final SuperviseInnerApprovalCallbackImpl superviseInnerApprovalCallbackImpl;
    private final TaskDelayApprovalCallbackImpl taskDelayApprovalCallbackImpl;
    private final TaskClosureApprovalCallbackImpl taskClosureApprovalCallbackImpl;
    private final TaskSummaryApprovalCallbackImpl taskSummaryApprovalCallbackImpl;
    @Override
    public void callback(ApprovalCallback callback) {
        //方案2: 使用工厂设计模式减少冗余代码，提高可拓展性
        CallbackService callbackService = callbackFactory.createService(callback.getType());
        if (ApprovalCallbackStatus.create.name().equals(callback.getStatus())) {
            //方案1: 需要频繁创建相应的业务对象做业务处理;
            if (ApprovalCallbackType.superviseInnerApproval.name().equals(callback.getType())) {
                //创建督办内部审批对象，执行相应业务处理;
                superviseInnerApprovalCallbackImpl.create(callback);
            } else if(ApprovalCallbackType.taskDelayApproval.name().equals(callback.getType())) {
                //创建任务延期审批对象，执行相应业务处理;
                taskDelayApprovalCallbackImpl.create(callback);
            } else if(ApprovalCallbackType.taskClosureApproval.name().equals(callback.getType())) {
                //创建任务办结审批对象，执行相应业务处理;
                taskClosureApprovalCallbackImpl.create(callback);
            } else if(ApprovalCallbackType.taskSummaryApproval.name().equals(callback.getType())) {
                //创建任务总结审批对象，执行相应业务处理;
                taskSummaryApprovalCallbackImpl.create(callback);
            }

            callbackService.create(callback);
        } else if(ApprovalCallbackStatus.refuse.name().equals(callback.getStatus())) {
            //方案1: 需要频繁创建相应的业务对象做业务处理;
            if (ApprovalCallbackType.superviseInnerApproval.name().equals(callback.getType())) {
                //创建督办内部审批对象，执行相应业务处理;
                superviseInnerApprovalCallbackImpl.refuse(callback);
            } else if(ApprovalCallbackType.taskDelayApproval.name().equals(callback.getType())) {
                //创建任务延期审批对象，执行相应业务处理;
                taskDelayApprovalCallbackImpl.refuse(callback);
            } else if(ApprovalCallbackType.taskClosureApproval.name().equals(callback.getType())) {
                //创建任务办结审批对象，执行相应业务处理;
                taskClosureApprovalCallbackImpl.refuse(callback);
            } else if(ApprovalCallbackType.taskSummaryApproval.name().equals(callback.getType())) {
                //创建任务总结审批对象，执行相应业务处理;
                taskSummaryApprovalCallbackImpl.refuse(callback);
            }

            callbackService.refuse(callback);
        } else if(ApprovalCallbackStatus.complete.name().equals(callback.getStatus())) {
            //方案1: 需要频繁创建相应的业务对象做业务处理;
            if (ApprovalCallbackType.superviseInnerApproval.name().equals(callback.getType())) {
                //创建督办内部审批对象，执行相应业务处理;
                superviseInnerApprovalCallbackImpl.create(callback);
            } else if(ApprovalCallbackType.taskDelayApproval.name().equals(callback.getType())) {
                //创建任务延期审批对象，执行相应业务处理;
                taskDelayApprovalCallbackImpl.complete(callback);
            } else if(ApprovalCallbackType.taskClosureApproval.name().equals(callback.getType())) {
                //创建任务办结审批对象，执行相应业务处理;
                taskClosureApprovalCallbackImpl.complete(callback);
            } else if(ApprovalCallbackType.taskSummaryApproval.name().equals(callback.getType())) {
                //创建任务总结审批对象，执行相应业务处理;
                taskSummaryApprovalCallbackImpl.complete(callback);
            }

            callbackService.complete(callback);
        }
    }
}
