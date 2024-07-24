package com.bti.ui.views.product;

import com.bti.model.Product;
import com.bti.services.ProductService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.faces.view.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.annotation.RequestScope;

@Component
public class ProductView {

    @Autowired
    private ProductService productService;

    private Product product;

    private int productId;

    @PostConstruct
    public void init() {
        this.product = productService.getProduct(productId).orElse(null);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
