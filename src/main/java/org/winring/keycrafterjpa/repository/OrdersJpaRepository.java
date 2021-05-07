package org.winring.keycrafterjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.winring.keycrafterjpa.domain.Orders;

public interface OrdersJpaRepository extends JpaRepository<Orders, Long>, JpaSpecificationExecutor<Orders> {
}
