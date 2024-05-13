package com.darkwiki.model;

import jakarta.persistence.*;
import org.springframework.data.domain.Persistable;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Region implements Persistable<Long>, Serializable, Comparable<Region> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "region")
    private Set<Vendor> vendorSet = new HashSet<>();

    public void addToVendorSet(Vendor vendor) {
        vendorSet.add(vendor);
        vendor.setRegion(this);
    }

    public void removeFromVendorSet(Vendor vendor) {
        vendorSet.remove(vendor);
        vendor.setRegion(null);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Region region) {
            return id.equals(region.id);
        } else {
            return false;
        }
    }

    @Override
    public int compareTo(Region o) {
        return id.compareTo(o.id);
    }

    @Override
    public boolean isNew() {
        return false;
    }

    @Override
    public String toString() {
        return "(%s) %s".formatted(id, name);
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Vendor> getVendorSet() {
        return vendorSet;
    }

    public void setVendorSet(Set<Vendor> vendorSet) {
        this.vendorSet = vendorSet;
    }

}
