package com.bti.services;

import com.bti.model.Order;
import com.bti.repositories.OrderRepository;
import com.bti.repositories.RegionRepository;
import jakarta.transaction.Transactional;
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

    public Collection<Order> getOrderCollectionByVendorId(Long vendorId) {
        return orderRepository.findByVendorId(vendorId);
    }

    public Collection<Order> getOrderCollectionByBuyerId(Long userId) {
        return orderRepository.findByBuyerId(userId);
    }

    public Order saveOrder(Order order) {
        orderRepository.save(order);
        return order;
    }

    @Transactional
    public boolean deleteOrder(Long id) {

        if (orderRepository.findById(id).isPresent()) {
            orderRepository.deleteById(id);

            return true;
        }

        return false;
    }
}
