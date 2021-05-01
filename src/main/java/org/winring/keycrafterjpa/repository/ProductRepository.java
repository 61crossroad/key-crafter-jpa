package org.winring.keycrafterjpa.repository;

import org.springframework.stereotype.Repository;
import org.winring.keycrafterjpa.domain.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ProductRepository {
    @PersistenceContext
    EntityManager em;

    public void save(Product product) {

    }
}
