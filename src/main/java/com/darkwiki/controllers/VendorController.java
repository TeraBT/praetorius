package com.darkwiki.controllers;

import com.darkwiki.auxiliaries.OrderReferenceGenerator;
import com.darkwiki.entities.Order;
import com.darkwiki.entities.Vendor;
import com.darkwiki.services.OrderService;
import com.darkwiki.services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
public class VendorController {

    @Autowired
    VendorService vendorService;

    private final OrderReferenceGenerator orderReferenceGenerator = new OrderReferenceGenerator();

    public Collection<Vendor> getAllVendors() {
        return vendorService.getAllVendors();
    }

    public void saveVendor(String name) {
        Vendor vendor = new Vendor();
        vendor.setName(name);
        vendorService.saveVendor(vendor);
    }

}
