package com.bti.repositories;

import com.bti.model.Order;

import java.util.Collection;
import java.util.Optional;

public interface OrderRepository extends AbstractRepository<Order, Long> {
    Optional<Order> findByOrderReference(String orderReference);

    Collection<Order> findByVendorId(Long vendorId);

    Collection<Order> findByBuyerId(Long buyerId);
}
