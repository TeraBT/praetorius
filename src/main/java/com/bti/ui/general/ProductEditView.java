package com.bti.ui.general;

import com.bti.controllers.OrderController;
import com.bti.controllers.ProductController;
import com.bti.model.Product;
import com.bti.model.ProductType;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.SortedSet;

@Component
@Scope("request")
public class ProductEditView {

    @Autowired
    private ProductController productController;

    private Product product;

    private String name;

    private ProductType productType;

    private String description;

    private String detailedDescription;

    private double pricePerUnit;

    private SortedSet<Integer> availableAmountSet;

    @PostConstruct
    public void init() {
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        this.product = (Product) httpSession.getAttribute("product");
        this.name = product.getName();
        this.productType = product.getProductType();
        this.description = product.getDescription();
        this.detailedDescription = product.getDetailedDescription();
        this.pricePerUnit = product.getPricePerUnit();
        this.availableAmountSet = product.getAvailableAmountSet();
    }

    public void editOrder(String name, ProductType productType, String description, String detailedDescription, double pricePerUnit, SortedSet<Integer> availableAmountSet) {

        // TODO: Continue.
        productController.saveProduct(product);
        FacesMessage msg = new FacesMessage("Product Edited.",
                "'%s' has been edited successfully.".formatted(product.getName()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public SortedSet<Integer> getAvailableAmountSet() {
        return availableAmountSet;
    }

    public void setAvailableAmountSet(SortedSet<Integer> availableAmountSet) {
        this.availableAmountSet = availableAmountSet;
    }
}
