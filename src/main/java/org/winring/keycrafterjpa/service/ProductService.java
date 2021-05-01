package org.winring.keycrafterjpa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.winring.keycrafterjpa.domain.Product;
import org.winring.keycrafterjpa.repository.ProductRepository;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public List<Product> findProducts() {
        return productRepository.findAll();
    }

    public Product findOne(Long id) {
        return productRepository.findOne(id);
    }
}
