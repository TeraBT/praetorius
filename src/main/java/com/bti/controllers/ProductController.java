package com.bti.controllers;

import com.bti.model.Product;
import com.bti.services.OrderService;
import com.bti.services.ProductService;
import com.bti.ui.views.product.ProductView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    OrderService orderService;

    public String initProductView(Long productId) {
        return "product/" + productId;
    }

    @GetMapping("/product/{productId}")
    public String initProductView(@PathVariable long productId) {
        System.out.println("Product ID: " + productId);
        this.product = productService.getProduct(productId).orElse(null);
        System.out.println(product);
        return "product";
    }

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
