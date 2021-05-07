package org.winring.keycrafterjpa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.winring.keycrafterjpa.domain.Product;
import org.winring.keycrafterjpa.domain.ProductSearch;
import org.winring.keycrafterjpa.domain.QProduct;
import org.winring.keycrafterjpa.repository.ProductJpaRepository;
import org.winring.keycrafterjpa.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Transactional
@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductJpaRepository productJpaRepository;

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public List<Product> findProducts() {
        return productRepository.findAll();
    }

    public Product findOne(Long id) {
        return productRepository.findOne(id);
    }

    public List<Product> findProductsQuerydsl(ProductSearch productSearch) {
        QProduct product = QProduct.product;
        Iterable<Product> result = productJpaRepository.findAll(
                product.name.contains(productSearch.getName())
                        .and(product.price.between(productSearch.getPriceMin(), productSearch.getPriceMax())));

        return StreamSupport.stream(result.spliterator(), false)
                .collect(Collectors.toList());
    }
}
