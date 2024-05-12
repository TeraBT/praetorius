package com.darkwiki.repositories;

import com.darkwiki.model.Order;

import java.util.Optional;

public interface OrderRepository extends AbstractRepository<Order, Long> {
    Optional<Order> findById(long id);

    Optional<Order> findByOrderReference(String orderReference);

}
