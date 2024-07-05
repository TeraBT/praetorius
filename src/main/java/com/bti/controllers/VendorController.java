package com.bti.controllers;

import com.bti.model.Vendor;
import com.bti.services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Collection;

@Controller
public class VendorController {

    @Autowired
    VendorService vendorService;

    public Collection<Vendor> getAllVendors() {
        return vendorService.getAllVendors();
    }

    public void saveVendor(String name) {
        Vendor vendor = new Vendor();
        vendor.setName(name);
        vendorService.saveVendor(vendor);
    }

    public void saveVendor(Vendor vendor) {
        vendorService.saveVendor(vendor);
    }

}
