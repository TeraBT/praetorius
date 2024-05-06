package com.darkwiki.services;

import com.darkwiki.model.Vendor;
import com.darkwiki.repositories.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class VendorService {

    @Autowired
    VendorRepository vendorRepository;

    public Collection<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    public Vendor saveVendor(String name) {
        Vendor vendor = new Vendor();
        vendor.setName(name);
        vendorRepository.save(vendor);
        return vendor;
    }

    public Vendor saveVendor(Vendor vendor) {
        vendorRepository.save(vendor);
        return vendor;
    }
}
