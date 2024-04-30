package com.darkwiki.entities;

import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;
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
    public String getId() {
        return id.toString();
    }

    @Override
    public boolean isNew() {
        return false;
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

    @Override
    public int compareTo(@NotNull Region o) {
        return 0;
    }
}
