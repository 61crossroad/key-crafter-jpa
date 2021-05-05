package org.winring.keycrafterjpa.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.winring.keycrafterjpa.domain.Member;
import org.winring.keycrafterjpa.domain.OrderSearch;
import org.winring.keycrafterjpa.domain.Orders;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

@Repository
public class OrdersRepository {
    @PersistenceContext
    EntityManager em;

    public void save(Orders orders) {
        em.persist(orders);
    }

    public Orders findOne(Long id) {
        return em.find(Orders.class, id);
    }

    public List<Orders> findAll(OrderSearch orderSearch) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Orders> cq = cb.createQuery(Orders.class);
        Root<Orders> o = cq.from(Orders.class);

        List<Predicate> criteria = new ArrayList<>();

        if (orderSearch.getOrderStatus() != null) {
            Predicate status = cb.equal(
                    o.get("status"), orderSearch.getOrderStatus());
            criteria.add(status);
        }

        if (StringUtils.hasText(orderSearch.getMemberName())) {
            Join<Orders, Member> m = o.join("member", JoinType.INNER);
            Predicate name = cb.like(
                    m.<String>get("name"),
                    "%" + orderSearch.getMemberName() + "%");
            criteria.add(name);
        }

        cq.where(
                cb.and(
                        criteria.toArray(
                                new Predicate[criteria.size()])));

        TypedQuery<Orders> query = em.createQuery(cq).setMaxResults(1000);
        return query.getResultList();
    }
}
