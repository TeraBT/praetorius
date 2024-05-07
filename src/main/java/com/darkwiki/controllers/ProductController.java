package com.darkwiki.controllers;

import com.darkwiki.model.Product;
import com.darkwiki.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Collection;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    public Collection<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    public void saveProduct(String name) {
        Product product = new Product();
        product.setName(name);
        productService.saveProduct(product);
    }

    public void saveProduct(Product product) {
        productService.saveProduct(product);
    }

}
