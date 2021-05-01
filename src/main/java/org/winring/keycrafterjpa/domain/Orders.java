package org.winring.keycrafterjpa.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ToString(exclude = {"member", "ordersProducts", "delivery"})
@Setter
@Getter
@Entity
public class Orders extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<OrdersProduct> ordersProducts = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public void setMember(Member member) {
        Collection<Orders> thisMemberOrders = this.member == null ? null : this.member.getOrders();
        super.setRelatedEntity(this, this.member, thisMemberOrders, member.getOrders());
        this.member = member;
    }

    public void addOrdersProduct(OrdersProduct ordersProduct) {
        this.ordersProducts.add(ordersProduct);
        ordersProduct.setOrders(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrders(this);
    }
}
