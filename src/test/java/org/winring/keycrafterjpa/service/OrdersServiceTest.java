package org.winring.keycrafterjpa.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.winring.keycrafterjpa.domain.*;
import org.winring.keycrafterjpa.repository.OrdersRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Transactional
@SpringBootTest
public class OrdersServiceTest {
    @PersistenceContext
    EntityManager em;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private OrdersRepository ordersRepository;

    @Test
    public void orderProductTest() {
        // Given
        Member member = this.createMember();

        int price = 33333;
        int stockQuantity = 144;
        int orderQuantity = 2;

        Product product = this.createBook("New Testament", price, stockQuantity);

        // When
        Long orderId = ordersService.order(member.getId(), product.getId(), orderQuantity);

        // Then
        Orders getOrder = ordersService.findOne(orderId);

        Assertions.assertEquals(OrderStatus.ORDERED, getOrder.getStatus(), "OrderStatus is ORDERED");
        Assertions.assertEquals(1, getOrder.getOrdersProducts().size(), "Assert order quantity");
        Assertions.assertEquals(price * orderQuantity, getOrder.getTotalPrice(), "Assert TotalPrice");
        Assertions.assertEquals(stockQuantity - orderQuantity, product.getQuantity());
    }

    @Test
    public void notEnoughProductQuantityTest() { }

    @Test
    public void cancelOrder() { }

    private Member createMember() {
        Member member = new Member();
        member.setName("member1");
        member.setAddress(new Address("Seoul", "Gangnam", "123-456"));
        em.persist(member);
        return member;
    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setQuantity(stockQuantity);
        book.setPrice(price);
        em.persist(book);
        return book;
    }
}
