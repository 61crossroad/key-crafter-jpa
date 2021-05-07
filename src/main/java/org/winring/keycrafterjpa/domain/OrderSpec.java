package org.winring.keycrafterjpa.domain;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;

public class OrderSpec {
    public static Specification<Orders> memberNameLike(final String memberName) {
        return new Specification<Orders>() {
            @Override
            public Predicate toPredicate(Root<Orders> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (!StringUtils.hasText(memberName))
                    return null;

                Join<Orders, Member> m = root.join("member", JoinType.INNER);
                return criteriaBuilder.like(m.<String>get("name"), "%" + memberName + "%");
            }
        };
    }

    public static Specification<Orders> orderStatusEq(final OrderStatus orderStatus) {
        return new Specification<Orders>() {
            @Override
            public Predicate toPredicate(Root<Orders> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (orderStatus == null)
                    return null;

                return criteriaBuilder.equal(root.get("status"), orderStatus);
            }
        };
    }
}
