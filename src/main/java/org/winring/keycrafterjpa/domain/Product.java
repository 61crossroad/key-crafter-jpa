package org.winring.keycrafterjpa.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.winring.keycrafterjpa.exception.NotEnoughQuantityException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ToString
@Setter
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
@Entity
public abstract class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer price;
    private Integer quantity;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "category")
    private List<Category> categories = new ArrayList<>();

    public void addQuantity(Integer quantity) {
        this.quantity += quantity;
    }

    public void removeQuantity(Integer quantity) {
        int restQuantity = this.quantity - quantity;

        if (restQuantity < 0) {
            throw new NotEnoughQuantityException("need more product.");
        }

        this.quantity = restQuantity;
    }
}
