//package com.yjl.service.specification;
//
//import com.yjl.domain.model.ApprovalCallback;
//import com.yjl.domain.dto.ApprovalCallbackDTO;
//import org.springframework.data.jpa.domain.Specification;
//import org.springframework.util.StringUtils;
//
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//@Slf4j
//@RequiredArgsConstructor
//@Service
//public interface ApprovalCallbackSpecification implements Specification<ApprovalCallback> {
//
//    private final ApprovalCallbackDTO condition;
//
//    public ApprovalCallbackSpecification(ApprovalCallbackDTO condition) {
//        this.condition = condition;
//    }
//
//    @Override
//    public Predicate toPredicate(Root<ApprovalCallback> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//        List<Predicate> ands = new ArrayList<>();
//        ands.add(cb.equal(root.get("deleted"), false));
//
//        ${setter}
//        return cb.and(ands.toArray(new Predicate[0]));
//    }
//}
