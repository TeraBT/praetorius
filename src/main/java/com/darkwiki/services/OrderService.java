package com.darkwiki.services;

import com.darkwiki.model.Order;
import com.darkwiki.repositories.OrderRepository;
import com.darkwiki.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private RegionRepository regionRepository;

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

    public boolean deleteOrder(Long id) {

        if (orderRepository.findById(id).isPresent()) {
            orderRepository.deleteById(id);

            return true;
        }

        return false;
    }
}
