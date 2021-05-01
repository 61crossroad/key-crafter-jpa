package org.winring.keycrafterjpa.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@ToString
@Setter
@Getter
@DiscriminatorValue("M")
@Entity
public class Movie extends Product {
    private String director;
    private String actor;
}
