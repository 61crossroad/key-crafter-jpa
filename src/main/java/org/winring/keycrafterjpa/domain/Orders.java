package org.winring.keycrafterjpa.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

@ToString(exclude = {"member", "ordersProduct", "delivery"})
@Setter
@Getter
@Entity
public class Orders extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(mappedBy = "orders", fetch = FetchType.LAZY)
    private OrdersProduct ordersProduct;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public void setMember(Member member) {
        Collection<Orders> thisMemberOrders = this.member == null ? null : this.member.getOrders();
        super.setRelatedEntity(this, this.member, thisMemberOrders, member.getOrders());
        this.member = member;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrders(this);
    }
}
