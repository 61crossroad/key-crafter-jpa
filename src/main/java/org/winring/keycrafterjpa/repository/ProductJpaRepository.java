package org.winring.keycrafterjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.winring.keycrafterjpa.domain.Product;

public interface ProductJpaRepository extends JpaRepository<Product, Long>, QuerydslPredicateExecutor<Product> {
}
