package com.darkwiki.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Persistable;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "order_table")
public class Order implements Persistable<String>, Serializable, Comparable<Order> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private String vendor;

    private String product;

    private double amount;

    public void setId(Long id) {
        this.id = id;
    }

    public String getId() {
        return id.toString();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public int compareTo(@NotNull Order o) {
        return 0;
    }

    @Override
    public boolean isNew() {
        return false;
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
