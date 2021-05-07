package org.winring.keycrafterjpa.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

@Setter
@Getter
public class OrderSearch {
    private String memberName;
    private OrderStatus orderStatus;

    public Specification<Orders> toSpecification() {
        return Specification.where(OrderSpec.memberNameLike(memberName))
                .and(OrderSpec.orderStatusEq(orderStatus));
    }
}
