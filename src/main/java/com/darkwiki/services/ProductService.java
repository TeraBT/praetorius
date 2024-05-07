package com.darkwiki.services;

import com.darkwiki.model.Product;
import com.darkwiki.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Collection<Product> getAllProducts() {
        return productRepository.findAll();
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
}
