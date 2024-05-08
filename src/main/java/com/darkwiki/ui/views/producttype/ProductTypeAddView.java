package com.darkwiki.ui.views.productTypetype;

import com.darkwiki.controllers.ProductTypeController;
import com.darkwiki.model.ProductType;
import com.darkwiki.ui.views.AbstractAddView;
import jakarta.enterprise.context.RequestScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.SortedSet;
import java.util.TreeSet;

@Component
@RequestScoped
public class ProductTypeAddView extends AbstractAddView {

    @Autowired
    ProductTypeController productTypeController;

    private String name;


    public void addProductType() {
        ProductType productType = new ProductType();

        if (name.isEmpty()) {
            productType.setName("UNNAMED");
        } else {
            productType.setName(name);
        }

        productTypeController.saveProductType(productType);
    }

    @Override
    public String getOutputString() {
        return "ProductType %s added".formatted(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}