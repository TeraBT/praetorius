package com.darkwiki.services;

import com.darkwiki.model.Order;
import com.darkwiki.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public Collection<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderByOrderReference(String orderReference) {
        return orderRepository.findByOrderReference(orderReference);
    }

    public Order saveOrder(Order order) {
        orderRepository.save(order);
        return order;
    }
}
