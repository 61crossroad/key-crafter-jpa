package org.winring.keycrafterjpa.repository;

import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.winring.keycrafterjpa.domain.OrderSearch;
import org.winring.keycrafterjpa.domain.Orders;
import org.winring.keycrafterjpa.domain.QMember;
import org.winring.keycrafterjpa.domain.QOrders;

import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class CustomOrderRepository extends QuerydslRepositorySupport {
    private final OrdersJpaRepository ordersJpaRepository;

    public CustomOrderRepository(OrdersJpaRepository ordersJpaRepository) {
        super(Orders.class);
        this.ordersJpaRepository = ordersJpaRepository;
    }

    public Orders findById(Long id) {
        return ordersJpaRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    public List<Orders> findAll(OrderSearch orderSearch) {
        QOrders orders = QOrders.orders;
        QMember member = QMember.member;

        JPQLQuery<Orders> query = from(orders);

        if (StringUtils.hasText(orderSearch.getMemberName())) {
            query.leftJoin(orders.member, member)
                    .where(member.name.contains(orderSearch.getMemberName()));
        }

        if (orderSearch.getOrderStatus() != null) {
            query.where(orders.status.eq(orderSearch.getOrderStatus()));
        }

        return query.fetch();
    }
}
