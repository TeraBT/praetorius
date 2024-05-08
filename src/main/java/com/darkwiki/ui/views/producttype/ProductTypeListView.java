package com.darkwiki.ui.views.producttype;

import com.darkwiki.controllers.ProductTypeController;
import com.darkwiki.model.ProductType;
import com.darkwiki.ui.views.AbstractListView;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequestScoped
public class ProductTypeListView extends AbstractListView<ProductType> {

    @Autowired
    ProductTypeController productTypeController;

    @PostConstruct
    public void init() {super.setCollection(productTypeController.getAllProductTypes());}
}