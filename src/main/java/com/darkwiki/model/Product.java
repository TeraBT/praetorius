package com.darkwiki.model;

import jakarta.persistence.*;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Product implements Persistable<Long>, Serializable, Comparable<Product> {

    @Id
    @GeneratedValue
    Long id;

    @Column
    private ProductType productType;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private double pricePerUnit;

    @ElementCollection
    @CollectionTable(name = "product_amounts", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "available_amount")
    private List<Integer> availableAmountList = new ArrayList<>();

    @Column
    private double shippingCost;

    public void addToAvailableAmountList(Integer amount) {
        availableAmountList.add(amount);
    }

    public void removeFromAvailableAmountList(Integer amount) {
        availableAmountList.remove(amount);
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(id);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product other)) return false;
        return id.equals(other.id);
    }

    @Override
    public int compareTo(Product o) {
        return id.compareTo(o.id);
    }

    @Override
    public boolean isNew() {
        return id == null;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
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

    public List<Integer> getAvailableAmountList() {
        return availableAmountList;
    }

    public void setAvailableAmountList(List<Integer> availableAmountList) {
        this.availableAmountList = availableAmountList;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }
}
