package com.bti.controllers;

import com.bti.auxiliaries.OrderReferenceGenerator;
import com.bti.model.Order;
import com.bti.model.Product;
import com.bti.services.OrderService;
import com.bti.services.ProductService;
import com.bti.ui.views.product.ProductView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Collection;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    OrderService orderService;

    @Autowired
    private ProductView productView;

    public void initProductView(Product product) {
        productView.setProduct(product);
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
