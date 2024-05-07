package com.darkwiki.ui.views.product;

import com.darkwiki.controllers.ProductController;
import com.darkwiki.model.Product;
import com.darkwiki.ui.views.AbstractListView;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequestScoped
public class ProductListView extends AbstractListView<Product> {

    @Autowired
    ProductController productController;

    @PostConstruct
    public void init() {super.setCollection(productController.getAllProducts());}
}