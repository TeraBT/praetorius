package com.darkwiki.ui.views.product;

import com.darkwiki.controllers.ProductController;
import com.darkwiki.model.Product;
import com.darkwiki.ui.views.AbstractAddView;
import jakarta.enterprise.context.RequestScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequestScoped
public class ProductAddView extends AbstractAddView {

    @Autowired
    ProductController productController;

    private String name;

    public void addProduct() {
        Product product = new Product();

        if (name.isEmpty()) {
            product.setName("UNNAMED");
        } else {
            product.setName(name);
        }

        productController.saveProduct(product);
    }

    @Override
    public String getOutputString() {
        return "Product %s added".formatted(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}