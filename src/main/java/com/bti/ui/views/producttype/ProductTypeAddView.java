package com.bti.ui.views.productTypetype;

import com.bti.controllers.ProductTypeController;
import com.bti.model.ProductType;
import com.bti.ui.views.AbstractAddView;
import jakarta.enterprise.context.RequestScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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