package com.darkwiki.services;

import com.darkwiki.model.Order;
import com.darkwiki.model.Vendor;
import com.darkwiki.repositories.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class VendorService {

    @Autowired
    VendorRepository vendorRepository;

    public Collection<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    public Collection<String> getAllVendorNames() {
        return getAllVendors().stream().map(Vendor::getName).collect(Collectors.toSet());
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

    public Optional<Vendor> getVendor(long id) {
        return vendorRepository.findById(id);
    }

    public Optional<Vendor> getVendor(String name) {
        return vendorRepository.findByName(name);
    }

    public boolean deleteVendor(Long id) {

        boolean wasPresent = vendorRepository.findById(id).isPresent();

        if (wasPresent) {
            vendorRepository.deleteById(id);
        }

        return wasPresent;
    }
}
