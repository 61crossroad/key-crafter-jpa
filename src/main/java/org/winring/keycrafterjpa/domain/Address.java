package org.winring.keycrafterjpa.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;

@ToString
@Setter
@Getter
@Embeddable
public class Address {
    private String city;
    private String street;
    private String zipcode;
}
