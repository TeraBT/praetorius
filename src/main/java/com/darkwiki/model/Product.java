package com.darkwiki.model;

import jakarta.persistence.*;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;
import java.util.*;

@Entity
public class Product implements Persistable<Long>, Serializable, Comparable<Product> {

    @Id
    @GeneratedValue
    Long id;


    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_type_id")
    private ProductType productType;

    @Column
    private String description;

    @Column
    private double pricePerUnit;

    @ElementCollection
    @CollectionTable(name = "product_amounts", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "available_amount")
    private Set<Integer> availableAmountSet = new HashSet<>();

    @Column
    private double shippingCost;

    @OneToMany(mappedBy = "product")
    private Set<Order> orderSet = new HashSet<Order>();

    public void addToAvailableAmountList(Integer amount) {
        availableAmountSet.add(amount);
    }

    public void removeFromAvailableAmountList(Integer amount) {
        availableAmountSet.remove(amount);
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

    public Set<Integer> getAvailableAmountSet() {
        return availableAmountSet;
    }

    public void setAvailableAmountSet(Set<Integer> availableAmountSet) {
        this.availableAmountSet = availableAmountSet;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public Set<Order> getOrderSet() {
        return orderSet;
    }

    public void setOrderSet(Set<Order> orderSet) {
        this.orderSet = orderSet;
    }
}
