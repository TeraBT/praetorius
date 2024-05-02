package com.darkwiki.model;

import jakarta.persistence.*;
import org.springframework.data.domain.Persistable;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Region implements Persistable<String>, Serializable, Comparable<Region> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "region")
    private Set<Vendor> vendorSet = new HashSet<>();

    public void addVendor(Vendor vendor) {
        vendorSet.add(vendor);
        vendor.setRegion(this);
    }

    public void removeVendor(Vendor vendor) {
        vendorSet.remove(vendor);
        vendor.setRegion(null);
    }


    @Override
    public String toString() {
        return super.toString();
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
    public String getId() {
        return id.toString();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Vendor> getVendorSet() {
        return vendorSet;
    }

    public void setVendorSet(Set<Vendor> vendorSet) {
        this.vendorSet = vendorSet;
    }

}
