package com.darkwiki.repositories;

import com.darkwiki.model.Product;
import com.darkwiki.model.ProductType;

import java.util.Optional;

public interface ProductTypeRepository extends AbstractRepository<ProductType, Long> {

    Optional<ProductType> findById(long id);

    Optional<ProductType> findByName(String name);

}
