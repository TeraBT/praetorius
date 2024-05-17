package com.darkwiki.model;

import jakarta.persistence.*;
import org.springframework.data.domain.Persistable;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Vendor implements Persistable<Long>, Serializable, Comparable<Vendor> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @OneToMany(mappedBy = "vendor")
    private Set<Product> productSet = new HashSet<>();

    @OneToMany(mappedBy = "vendor")
    private Set<Order> orderSet = new HashSet<>();

    public void addToProductSet(Product product) {
        if (product != null) {
            productSet.add(product);
            product.setVendor(this);
        }
    }

    public void removeFromProductSet(Product product) {
        if (product != null) {
            productSet.remove(product);
            product.setVendor(null);
        }
    }

    public void addToOrderSet(Order order) {
        if (order != null) {
            orderSet.add(order);
            order.setVendor(this);
        }
    }

    public void removeFromOrderSet(Order order) {
        if (order != null) {
            orderSet.remove(order);
            order.setVendor(null);
        }
    }

    public void setRegion(Region region) {
        if (region != null) {
            region.getVendorSet().add(this);
        }

        if (this.region != null) {
            this.region.getVendorSet().remove(this);
        }

        this.region = region;
    }

    @Override
    public String toString() {
        return "(%s) %s".formatted(id, name);
    }

    @Override
    public boolean isNew() {
        return false;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Vendor vendor) {
            return id.equals(vendor.id);
        } else {
            return false;
        }
    }

    @Override
    public int compareTo(Vendor o) {
        return id.compareTo(o.id);
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Region getRegion() {
        return region;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Product> getProductSet() {
        return productSet;
    }

    public void setProductSet(Set<Product> productSet) {
        this.productSet = productSet;
    }

    public Set<Order> getOrderSet() {
        return orderSet;
    }

    public void setOrderSet(Set<Order> orderSet) {
        this.orderSet = orderSet;
    }
}
