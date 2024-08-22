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

    private boolean isVisible;

    private boolean isEdited;

    @PostConstruct
    public void init() {
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        this.product = (Product) httpSession.getAttribute("product");
        initializeVariables();
    }

    public void initializeVariables() {
        name = product.getName();
        productType = product.getProductType();
        description = product.getDescription();
        detailedDescription = product.getDetailedDescription();
        pricePerUnit = product.getPricePerUnit();
        availableAmountSet = product.getAvailableAmountSet();
        // TODO: ?Shipping cost?
        this.isVisible = product.isVisible();
    }

    public void editOrder(String name, ProductType productType, String description, String detailedDescription, double pricePerUnit, SortedSet<Integer> availableAmountSet, boolean isVisible) {

        // TODO: Continue.
        productController.saveProduct(product);
        FacesMessage msg = new FacesMessage("Product Edited.",
                "'%s' has been edited successfully.".formatted(product.getName()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }


    public void resetName() {
        name = product.getName();
        isEdited = false;
    }

    public void resetProductType() {
        productType = product.getProductType();
        isEdited = false;
    }

    public void resetDescription() {
        description = product.getDescription();
        isEdited = false;
    }

    public void resetDetailedDescription() {
        detailedDescription = product.getDetailedDescription();
        isEdited = false;
    }

    public void resetPricePerUnit() {
        pricePerUnit = product.getPricePerUnit();
        isEdited = false;
    }

    public void resetAvailableAmountSet() {
        availableAmountSet = product.getAvailableAmountSet();
        isEdited = false;
    }

    public void resetIsVisible() {
        isVisible = product.isVisible();
        isEdited = false;
    }

    public void updateAndSaveProduct() {
        product.setName(name);
        product.setProductType(productType);
        product.setDescription(description);
        product.setDetailedDescription(detailedDescription);
        product.setPricePerUnit(pricePerUnit);
        product.setAvailableAmountSet(availableAmountSet);
        product.setVisible(isVisible);

        productController.saveProduct(product);
        FacesMessage msg = new FacesMessage("Product Edited.",
                "'%s' has been edited successfully.".formatted(product.getName()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void editName() {
        product.setName(name);
        productController.saveProduct(product);
        this.isEdited = true;
    }

    public void editProductType() {
        product.setProductType(productType);
        productController.saveProduct(product);
        this.isEdited = true;
    }

    public void editDescription() {
        product.setDescription(description);
        productController.saveProduct(product);
        this.isEdited = true;
    }

    public void editDetailedDescription() {
        product.setDetailedDescription(detailedDescription);
        productController.saveProduct(product);
        this.isEdited = true;
    }

    public void editPricePerUnit() {
        product.setPricePerUnit(pricePerUnit);
        productController.saveProduct(product);
        this.isEdited = true;
    }

    public void editAvailableAmountSet() {
        product.setAvailableAmountSet(availableAmountSet);
        productController.saveProduct(product);
        this.isEdited = true;
    }

    public void editIsVisible() {
        product.setVisible(isVisible);
        productController.saveProduct(product);
        this.isEdited = true;
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

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public boolean isEdited() {
        return isEdited;
    }

    public void setEdited(boolean edited) {
        isEdited = edited;
    }
}
