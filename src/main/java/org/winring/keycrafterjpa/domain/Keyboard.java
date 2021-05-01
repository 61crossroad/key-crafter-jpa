package org.winring.keycrafterjpa.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@ToString
@Setter
@Getter
@DiscriminatorValue("K")
@Entity
public class Keyboard extends Product {
    private String brand;
    private KeyLayout keyLayout;
    private SwitchType switchType;
}
