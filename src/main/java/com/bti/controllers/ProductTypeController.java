package com.bti.controllers;

import com.bti.model.ProductType;
import com.bti.services.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Collection;

@Controller
public class ProductTypeController {

    @Autowired
    ProductTypeService productTypeService;

    public Collection<ProductType> getAllProductTypes() {
        return productTypeService.getAllProductTypes();
    }

    public void saveProductType(String name) {
        ProductType productType = new ProductType();
        productType.setName(name);
        productTypeService.saveProductType(productType);
    }

    public void saveProductType(ProductType productType) {
        productTypeService.saveProductType(productType);
    }

}
