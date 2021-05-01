package org.winring.keycrafterjpa.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@ToString
@Setter
@Getter
@DiscriminatorValue("K")
@Entity
public class Keyboard extends Product {
    private String brand;
    private KeyLayout keyLayout;
    private SwitchType switchType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
