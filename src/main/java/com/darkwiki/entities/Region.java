package com.darkwiki.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Persistable;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

public class Region implements Persistable<String>, Serializable, Comparable<Region> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    Long id;

    Set<Vendor> vendorSet;

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
