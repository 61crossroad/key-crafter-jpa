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

    public static Orders createOrders(Member member, Delivery delivery, OrdersProduct... ordersProducts) {
        Orders order = new Orders();
        order.setMember(member);
        order.setDelivery(delivery);
        for (OrdersProduct ordersProduct : ordersProducts) {
            order.addOrdersProduct(ordersProduct);
        }
        order.setStatus(OrderStatus.ORDERED);
        return order;
    }

    public void cancel() {
        if (delivery.getStatus().equals(DeliveryStatus.DEPARTURE)) {
            throw new RuntimeException(
                    "이미 배송완료된 상품은 취소가 불가능합니다."
            );
        }

        this.setStatus(OrderStatus.CANCELLED);
        ordersProducts.forEach(OrdersProduct::cancel);
    }

    public int getTotalPrice() {
        return ordersProducts.stream()
                .mapToInt(OrdersProduct::getTotalPrice)
                .sum();
    }

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
