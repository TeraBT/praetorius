package com.bti.repositories;

import com.bti.model.Product;
import com.bti.model.ProductType;

import java.util.Collection;
import java.util.Optional;

public interface ProductRepository extends AbstractRepository<Product, Long> {
    Optional<Product> findByName(String name);

    Collection<Product> findAllByProductType(ProductType productType);
}
