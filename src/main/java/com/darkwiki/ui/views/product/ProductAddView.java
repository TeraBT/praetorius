package com.darkwiki.ui.views.product;

import com.darkwiki.controllers.ProductController;
import com.darkwiki.model.Product;
import com.darkwiki.ui.views.AbstractAddView;
import jakarta.enterprise.context.RequestScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequestScoped
public class ProductAddView extends AbstractAddView {

    @Autowired
    ProductController productController;

    private String name;

    private String description;

    private double pricePerUnit;

    private String availableAmountAsString;

    private double shippingCost;

    public void addProduct() {
        Product product = new Product();

        if (name.isEmpty()) {
            product.setName("UNNAMED");
        } else {
            product.setName(name);
        }

        if (description.isEmpty()) {
            product.setDescription("NONE");
        } else {
            product.setDescription(description);
        }

        product.setPricePerUnit(pricePerUnit);

        if (availableAmountAsString.isEmpty()) {
            product.setAvailableAmountSet(new TreeSet<>());
        } else {
            SortedSet<Integer> availableAmountSet = new TreeSet<>();
            Arrays.stream(availableAmountAsString.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .forEach(availableAmountSet::add);

            product.setAvailableAmountSet(availableAmountSet);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public String getAvailableAmountAsString() {
        return availableAmountAsString;
    }

    public void setAvailableAmountAsString(String availableAmountAsString) {
        this.availableAmountAsString = availableAmountAsString;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }
}