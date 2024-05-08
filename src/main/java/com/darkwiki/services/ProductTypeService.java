package com.darkwiki.services;

import com.darkwiki.model.ProductType;
import com.darkwiki.repositories.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class ProductTypeService {

    @Autowired
    ProductTypeRepository productTypeRepository;

    public Collection<ProductType> getAllProductTypes() {
        return productTypeRepository.findAll();
    }

    public ProductType saveProductType(String name) {
        ProductType productType = new ProductType();
        productType.setName(name);
        productTypeRepository.save(productType);
        return productType;
    }

    public ProductType saveProductType(ProductType productType) {
        productTypeRepository.save(productType);
        return productType;
    }
}
