package org.winring.keycrafterjpa.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@ToString
@Setter
@Getter
@DiscriminatorValue(value = "A")
@Entity
public class Album extends Product {
    private String artist;
    private String etc;
}
