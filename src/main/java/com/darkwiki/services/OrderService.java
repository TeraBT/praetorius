package com.darkwiki.services;

import com.darkwiki.entities.Order;
import com.darkwiki.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public Collection<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order saveOrder(Order order) {
        orderRepository.save(order);
        return order;
    }
}
