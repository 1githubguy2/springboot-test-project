package com.yjl.domain.mapper;

import com.yjl.domain.dto.ApprovalCallbackDTO;
import com.yjl.domain.model.ApprovalCallback;
import lombok.Data;

@Data
public class ApprovalCallbackDTOMapper {

    public static ApprovalCallback copy(ApprovalCallbackDTO source, ApprovalCallback target) {
        if (source == null) {
            return target;
        }
        if (target.getId() == null) {
            target.setId(source.getId());
        }
        target.setUsername(source.getUsername());
        target.setAddress(source.getAddress());
        target.setType(source.getType());
        target.setStatus(source.getStatus());
        
        return target;
    }

    public static ApprovalCallbackDTO copy(ApprovalCallback source, ApprovalCallbackDTO target) {
        if (source == null) {
            return target;
        }
        if (target.getId() == null) {
            target.setId(source.getId());
        }
        target.setUsername(source.getUsername());
        target.setAddress(source.getAddress());
        target.setType(source.getType());
        target.setStatus(source.getStatus());
        
        return target;
    }
}
