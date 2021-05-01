package org.winring.keycrafterjpa.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@ToString(exclude = "orders")
@Setter
@Getter
@Entity
public class Delivery extends BaseEntity {
    @OneToOne(mappedBy = "delivery")
    private Orders orders;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;
}
