package org.winring.keycrafterjpa.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@ToString
@Setter
@Getter
@DiscriminatorValue("B")
@Entity
public class Book extends Product {
    private String author;
    private String isbn;
}
