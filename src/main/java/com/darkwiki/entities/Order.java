package com.darkwiki.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Order {


    @Id
    private Long id;

    private String vendor;

    private String product;

    private double amount;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
