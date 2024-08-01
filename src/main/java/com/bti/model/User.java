package com.bti.model;

import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Persistable;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "praetorius_user")
public class User implements Persistable<String>, Serializable, Comparable<User> {
//    @Serial
//    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(length = 100)
    private String username;

    private String password;

    private String email;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role")
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Set<Role> roleSet;

    @Column
    private Long vendorId;

    boolean enabled;

    @Override
    public String getId() {
        return username;
    }

    @Override
    public boolean isNew() {
        return false;
    }

    @Override
    public int compareTo(@NotNull User user) {
        return 0;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roles) {
        this.roleSet = roles;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
