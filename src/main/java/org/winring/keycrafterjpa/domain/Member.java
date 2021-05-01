package org.winring.keycrafterjpa.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ToString(exclude = "orders")
@Setter
@Getter
@Entity
public class Member extends BaseEntity {

    public Member() {}

    private String password;
    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Orders> orders = new ArrayList<>();

    private void setOrders(Orders orders) {
        this.orders.add(orders);
        orders.setMember(this);
    }
}
