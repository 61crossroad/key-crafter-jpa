package org.winring.keycrafterjpa.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderSearch {
    private String memberName;
    private OrderStatus orderStatus;
}
