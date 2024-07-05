package com.bti.repositories;

import com.bti.model.ProductType;

import java.util.Optional;

public interface ProductTypeRepository extends AbstractRepository<ProductType, Long> {

    Optional<ProductType> findByName(String name);

}
