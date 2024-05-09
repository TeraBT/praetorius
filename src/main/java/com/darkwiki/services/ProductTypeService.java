package com.darkwiki.services;

import com.darkwiki.model.ProductType;
import com.darkwiki.model.Region;
import com.darkwiki.repositories.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductTypeService {

    @Autowired
    ProductTypeRepository productTypeRepository;

    public Collection<ProductType> getAllProductTypes() {
        return productTypeRepository.findAll();
    }

    public Collection<String> getAllProductTypeNames() {
        return getAllProductTypes().stream().map(ProductType::getName).collect(Collectors.toSet());
    }

    public ProductType saveProductType(ProductType productType) {
        productTypeRepository.save(productType);
        return productType;
    }

    public ProductType saveProductType(String name) {
        ProductType productType = new ProductType();
        productType.setName(name);
        productTypeRepository.save(productType);
        return productType;
    }

    public Optional<ProductType> getProductType(long id) {
        return productTypeRepository.findById(id);
    }

    public Optional<ProductType> getProductType(String name) {
        return productTypeRepository.findByName(name);
    }
}
