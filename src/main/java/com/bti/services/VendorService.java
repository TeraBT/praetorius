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

    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;

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

            vendor.get().getProductSet().forEach(p -> {
                p.setVendor(null);
                productService.saveProduct(p);
            });
            vendor.get().getOrderSet().forEach(o -> {
                o.setVendor(null);
                orderService.saveOrder(o);
            });
            vendorRepository.deleteById(id);

            return true;
        }

        return false;
    }
}
