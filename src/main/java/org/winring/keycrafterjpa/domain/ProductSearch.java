package org.winring.keycrafterjpa.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductSearch {
    private String name;
    private Integer priceMin;
    private Integer priceMax;
}
