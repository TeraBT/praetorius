package com.bti.ui.admin.order;

import com.bti.controllers.OrderController;
import com.bti.controllers.VendorController;
import com.bti.model.Vendor;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequestScoped
public class OrderView {

    @Autowired
    VendorController vendorController;

    private Collection<Vendor> vendors;

    private String vendor;

    private String product;

    private Integer amount;

    private String selectedRegion;


    @Autowired
    private OrderController orderController;

    @PostConstruct
    public void init() {
//        vendors = vendorController.getAllVendors();

    }

    public void createAndSaveOrder() {
        orderController.saveOrder(vendor, product, amount);
    }


    public void setVendors(Collection<Vendor> vendors) {
        this.vendors = vendors;
    }

    public String getSelectedRegion() {
        return selectedRegion;
    }

    public void setSelectedRegion(String selectedRegion) {
        this.selectedRegion = selectedRegion;
    }


    public Collection<Vendor> getVendors() {
        return vendors;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

}