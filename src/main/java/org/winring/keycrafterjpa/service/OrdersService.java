package org.winring.keycrafterjpa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.winring.keycrafterjpa.domain.*;
import org.winring.keycrafterjpa.repository.MemberRepository;
import org.winring.keycrafterjpa.repository.OrdersRepository;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class OrdersService {
    private final MemberRepository memberRepository;
    private final OrdersRepository ordersRepository;
    private final ProductService productService;

    public Long order(Long memberId, Long productId, int quantity) {
        // Find Entities
        Member member = memberRepository.findOne(memberId);
        Product product = productService.findOne(productId);

        Delivery delivery = new Delivery(member.getAddress());
        OrdersProduct ordersProduct =
                OrdersProduct.createOrdersProduct(
                        product, product.getPrice(), quantity);
        Orders orders = Orders.createOrders(member, delivery, ordersProduct);

        ordersRepository.save(orders);
        return orders.getId();
    }

    public void cancelOrder(Long orderId) {
        Orders orders = ordersRepository.findOne(orderId);
        orders.cancel();
    }

    public Orders findOne(Long id) {
        return ordersRepository.findOne(id);
    }

    public List<Orders> findOrders(OrderSearch orderSearch) {
        return ordersRepository.findAll(orderSearch);
    }
}
