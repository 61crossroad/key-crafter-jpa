package org.winring.keycrafterjpa.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.winring.keycrafterjpa.domain.Movie;
import org.winring.keycrafterjpa.domain.Product;
import org.winring.keycrafterjpa.domain.ProductSearch;

import java.util.List;


@Transactional
@SpringBootTest
public class ProductServiceTest {
    @Autowired
    private ProductService productService;

    private final String movieName = "The Shawshank Redemption";

    @Test
    public void createProduct() {
        this.createInitialProduct();

        Product product = productService.findOne(1L);
        Assertions.assertEquals(product.getName(), movieName);
    }

    @Test
    public void findProducts() {
        this.createInitialProduct();

        final String requestedName = "Redemption";
        ProductSearch productSearch = new ProductSearch();
        productSearch.setName(requestedName);
        productSearch.setPriceMin(10000);
        productSearch.setPriceMax(40000);

        List<Product> results = productService.findProductsQuerydsl(productSearch);

        results.forEach(row -> Assertions.assertTrue(row.getName().contains("Redem")
                && productSearch.getPriceMin() <= row.getPrice()
                && row.getPrice() <= productSearch.getPriceMax()));
    }

    private void createInitialProduct() {
        Product movie = new Movie();
        movie.setName(movieName);
        movie.setPrice(30000);
        movie.setQuantity(10);
        productService.saveProduct(movie);
    }
}
