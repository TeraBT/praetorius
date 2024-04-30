package com.darkwiki.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Persistable;

import java.io.Serial;
import java.io.Serializable;

@Entity
public class Vendor implements Persistable<String>, Serializable, Comparable<Vendor> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    Long id;

    @Override
    public String getId() {
        return id.toString();
    }

    @Override
    public int compareTo(@NotNull Vendor o) {
        return 0;
    }

    @Override
    public boolean isNew() {
        return false;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
