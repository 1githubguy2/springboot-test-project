//package com.yjl.controller;
//
//import com.yjl.domain.model.ApprovalCallback;
//import com.yjl.domain.dto.ApprovalCallbackDTO;
//import com.yjl.domain.dto.ApprovalCallbackDTOMapper;
//import com.yjl.service.facade.ApprovalCallbackService;
//
//import org.springframework.bean.factory.annotation.Autowired;
//import org.springframework.data.domain.Example;
//import org.springframework.data.domain.ExampleMatcher;
//import org.springframework.data.domain.Sort;
//import org.springframework.web.bind.annotation.*;
//import io.swagger.annotation.*;
//import lombok.RequiredArgsConstructor;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RequiredArgsConstructor
//@RestController
//@RequestMapping("/api/approvalCallback")
//public class ApprovalCallbackController {
//ApprovalCallbackController
//    private static final String ENTITY_NAME = "ApprovalCallback";
//
//    private final ApprovalCallbackService = entityService;
//
//    @GetMapping("/{id}")
//    public ResponseDTO<ApprovalCallbackDTO> load(@PathVariable("id") String id) {
//        ApprovalCallback entity = entityService.findById(id);
//        if (entity == null) {
//            return null;
//        }
//        return ResponseDTO.ok(ApprovalCallbackDTOMapper.copy(entity, new ApprovalCallbackDTO()));
//    }
//
//    @PostMapping
//    public ResponseDTO<ApprovalCallbackDTO> create(@RequestBody ApprovalCallbackDTO entityDTO) {
//        ApprovalCallback entity = entityService.insert(ApprovalCallbackDTOMapper.copy(entityDTO, new ApprovalCallback()));
//        return ResponseDTO.created(ApprovalCallbackDTOMapper.copy(entity, new ApprovalCallbackDTO()));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseDTO<ApprovalCallbackDTO> update(@PathVariable("id") String id, @RequestBody ApprovalCallbackDTO entityDTO) {
//        ApprovalCallback entity = entityService.update(ApprovalCallbackDTOMapper.copy(entityDTO, entityService.findById(id)));
//        return ResponseDTO.created(ApprovalCallbackDTOMapper.copy(entity, new ApprovalCallbackDTO()));
//    }
//
//    @DeleteMapping("/{ids}")
//    public ResponseDTO<Void> delete(@PathVariable("id") String ids) {
//        entityService.weakDeleteByIds(Arrays.asList(ids.split(",")));
//        return ResponseDTO.noContent;
//    }
//
//    @PostMapping("/pageList")
//    public ResponseDTO<PaginationResultDTO<ApprovalCallbackDTO>> findPage(@RequestBody PaginationDTO<ApprovalCallbackDTO> paginationDTO) {
//        paginationDTO.setSort(Sort.by(Sort.Order.desc("createTime")));
//        ApprovalCallbackDTO condition = paginationDTO.getCondition();
//        ApprovalCallback approvalCallback = ApprovalCallbackDTOMapper.copy(condition, new ApprovalCallback());
//        ExampleMatcher matcher = ExampleMatcher.matching();//查询规则
//        PaginationDTO<ApprovalCallback> page = entityService.findPage(Example.of(approvalCallback, matcher), paginationDTO);
//        List<ApprovalCallbackDTO> dtos = page.getData().stream().map(entity -> ApprovalCallbackDTOMapper.copy(entity, new ApprovalCallbackDTO())).collect(Collectors.toList());
//        return ResponseDTO.ok(PaginationResultDTO.of(page, dtos));
//    }
//}
