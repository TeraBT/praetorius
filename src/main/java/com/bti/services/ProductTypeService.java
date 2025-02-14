package com.bti.services;

import com.bti.model.ProductType;
import com.bti.repositories.ProductTypeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProductTypeService {

    @Autowired
    ProductTypeRepository productTypeRepository;

    @Autowired
    private ProductService productService;

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

    @Transactional
    public boolean deleteProductType(Long id) {

        Optional<ProductType> productType = productTypeRepository.findById(id);

        if (productType.isPresent()) {

            productType.get().getProductSet().forEach(p -> {
                p.setProductType(null);
                productService.saveProduct(p);
            });
            productTypeRepository.deleteById(id);

            return true;
        }

        return false;
    }
}
