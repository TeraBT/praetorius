package com.bti.ui.views.product;

import com.bti.model.Product;
import com.bti.services.ProductService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Map;

@Component
public class ProductView {

    @Autowired
    private ProductService productService;

    private Product product;

    private Integer selectedAmount;


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getSelectedAmount() {
        return selectedAmount;
    }

    public void setSelectedAmount(Integer selectedAmount) {
        this.selectedAmount = selectedAmount;
    }
}
