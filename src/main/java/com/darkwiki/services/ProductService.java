package com.darkwiki.services;

import com.darkwiki.model.Product;
import com.darkwiki.model.ProductType;
import com.darkwiki.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    private OrderService orderService;

    public Collection<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Collection<String> getAllProductNames() {
        return getAllProducts().stream().map(Product::getName).collect(Collectors.toSet());
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

            product.get().getOrderSet().forEach(o -> {
                o.setProduct(null);
                orderService.saveOrder(o);
            });
            product.get().removeFromAvailableAmountSet(product.get().getAvailableAmountSet());
            productRepository.deleteById(id);

            return true;
        }

        return false;
    }
}
