package com.darkwiki.repositories;

import com.darkwiki.entities.Order;

import java.util.Optional;

public interface OrderRepository extends AbstractRepository<Order, Long> {
    Order findById(long id);

    Optional<Order> findByOrderReference(String orderReference);
}
