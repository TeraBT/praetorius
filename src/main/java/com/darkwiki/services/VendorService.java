package com.darkwiki.services;

import com.darkwiki.entities.Order;
import com.darkwiki.entities.Vendor;
import com.darkwiki.repositories.OrderRepository;
import com.darkwiki.repositories.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class VendorService {

    @Autowired
    VendorRepository vendorRepository;

    public Collection<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }


    public Vendor saveVendor(Vendor vendor) {
        vendorRepository.save(vendor);
        return vendor;
    }
}
