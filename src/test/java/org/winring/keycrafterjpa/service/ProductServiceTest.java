package org.winring.keycrafterjpa.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.winring.keycrafterjpa.domain.Movie;
import org.winring.keycrafterjpa.domain.Product;


import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@SpringBootTest
public class ProductServiceTest {
    @Autowired
    private ProductService productService;

    private String movieName = "The Shawshank Redemption";

    @Test
    public void createProduct() {
        Product movie = new Movie();
        movie.setName(movieName);
        movie.setPrice(30000);
        movie.setQuantity(10);
        productService.saveProduct(movie);
    }

    @AfterEach
    public void assertTest() {
        Product product = productService.findOne(1L);
        assertEquals(product.getName(), movieName);
    }
}
