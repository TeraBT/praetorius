package com.bti.services;

import com.bti.model.Vendor;
import com.bti.repositories.VendorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
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

    @Transactional
    public boolean deleteVendor(Long id) {

        Optional<Vendor> vendor = vendorRepository.findById(id);

        if (vendor.isPresent()) {

            Set.copyOf(vendor.get().getProductSet()).forEach(vendor.get()::removeFromProductSet);
            Set.copyOf(vendor.get().getOrderSet()).forEach(vendor.get()::removeFromOrderSet);
            vendorRepository.deleteById(id);

            return true;
        }

        return false;
    }
}
