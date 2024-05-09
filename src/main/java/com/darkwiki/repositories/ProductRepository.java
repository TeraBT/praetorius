package com.darkwiki.repositories;

import com.darkwiki.model.Product;

import java.util.Collection;
import java.util.Optional;

public interface ProductRepository extends AbstractRepository<Product, Long> {
    Optional<Product> findById(long id);

    Optional<Product> findByName(String name);

}
