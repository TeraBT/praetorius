package com.darkwiki.ui.views;

import com.darkwiki.controllers.VendorController;
import com.darkwiki.model.Vendor;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequestScoped
public class VendorListView {

    @Autowired
    VendorController VendorController;

    Collection<Vendor> VendorCollection;

    public Collection<Vendor> listAllVendors() {
        return VendorCollection;
    }

    @PostConstruct
    public void init() {
        VendorCollection = VendorController.getAllVendors();
    }
}