package com.bti.services;

import com.bti.model.Product;
import com.bti.model.ProductType;
import com.bti.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    private OrderService orderService;

    public Collection<Product> getProductCollection() {
        return productRepository.findAll();
    }

    public Collection<Product> getProductCollectionSortedByName() {
        return productRepository.findAll().stream().sorted(Comparator.comparing(Product::getName)).collect(Collectors.toList());
    }

    public Collection<Product> getProductCollectionByVendorIdSortedByName(Long vendorId) {
        return getProductCollectionByVendorId(vendorId).stream()
                .sorted(Comparator.comparing(Product::getName)).collect(Collectors.toList());
    }

    public Collection<String> getAllProductNames() {
        return getProductCollection().stream().map(Product::getName).collect(Collectors.toSet());
    }

    public Collection<Product> getProductCollectionByVendorId(Long vendorId) {
        return productRepository.findByVendorId(vendorId);
    }

    public Product saveProduct(String name) {
        Product product = new Product();
        product.setName(name);
        productRepository.save(product);
        return product;
    }

    public Product saveProduct(Product product) {
        productRepository.save(product);
        return product;
    }

    public Optional<Product> getProduct(long id) {
        return productRepository.findById(id);
    }

    public Optional<Product> getProduct(String name) {
        return productRepository.findByName(name);
    }

    public Collection<Product> getAllProductsByProductType(ProductType productType) {
        return productRepository.findAllByProductType(productType);
    }

    @Transactional
    public boolean deleteProduct(Long id) {

        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {

            Set.copyOf(product.get().getOrderSet()).forEach(product.get()::removeFromOrderSet);
            Set.copyOf(product.get().getAvailableAmountSet()).forEach(product.get()::removeFromAvailableAmountSet);
            productRepository.deleteById(id);

            return true;
        }

        return false;
    }
}
