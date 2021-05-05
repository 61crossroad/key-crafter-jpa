package org.winring.keycrafterjpa.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@ToString(exclude = {"product", "orders"})
@Setter
@Getter
@Entity
public class OrdersProduct extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_id")
    private Orders orders;

    private Integer price;
    private Integer quantity;

    public static OrdersProduct createOrdersProduct(Product product, int orderPrice, int quantity) {
        OrdersProduct ordersProduct = new OrdersProduct();
        ordersProduct.setProduct(product);
        ordersProduct.setPrice(orderPrice);
        ordersProduct.setQuantity(quantity);

        product.removeQuantity(quantity);
        return ordersProduct;
    }

    public void cancel() {
        this.getProduct().addQuantity(this.quantity);
    }

    public int getTotalPrice() {
        return this.getPrice() * this.getQuantity();
    }
}
