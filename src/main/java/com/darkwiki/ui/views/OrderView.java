package com.darkwiki.ui.views;

import com.darkwiki.controllers.OrderController;
import com.darkwiki.controllers.VendorController;
import com.darkwiki.model.Vendor;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.model.SelectItem;
import jakarta.faces.model.SelectItemGroup;
import jakarta.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
@RequestScoped
public class OrderView {

    @Autowired
    VendorController vendorController;

    private Collection<Vendor> vendors;

    private String vendor;

    private String product;

    private double amount;

    private String selectedRegion;
    private List<SelectItem> regionGroupList;
    @Autowired
    private OrderController orderController;

    @PostConstruct
    public void init() {
//        vendors = vendorController.getAllVendors();


        regionGroupList = new ArrayList<>();

        SelectItemGroup euGroup = new SelectItemGroup("EU");
        euGroup.setSelectItems(
                new SelectItem("Germany", "Germany"),
                new SelectItem("UK", "UK")
//                new SelectItem("Slovakia", "Slovakia")
        );

        SelectItemGroup usGroup = new SelectItemGroup("US");
        usGroup.setSelectItems(
//                new SelectItem("US West Coast", "US West Coast"),
                new SelectItem("US North", "US North"),
                new SelectItem("US South", "US South")
        );

        regionGroupList.add(euGroup);
        regionGroupList.add(usGroup);
    }

    public void createAndSaveOrder() {
        orderController.saveOrder(vendor, product, amount);
    }


//    public VendorController getVendorController() {
//        return vendorController;
//    }

//    public void setVendorController(VendorController vendorController) {
//        this.vendorController = vendorController;
//    }

    public void setVendors(Collection<Vendor> vendors) {
        this.vendors = vendors;
    }

    public String getSelectedRegion() {
        return selectedRegion;
    }

    public void setSelectedRegion(String selectedRegion) {
        this.selectedRegion = selectedRegion;
    }

    public List<SelectItem> getRegionGroupList() {
        return regionGroupList;
    }

    public void setRegionGroupList(List<SelectItem> regionGroupList) {
        this.regionGroupList = regionGroupList;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}