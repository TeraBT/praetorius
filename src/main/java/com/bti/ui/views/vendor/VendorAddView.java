package com.bti.ui.views.vendor;

import com.bti.controllers.RegionController;
import com.bti.controllers.VendorController;
import com.bti.model.Region;
import com.bti.model.Vendor;
import com.bti.ui.views.AbstractAddView;
import jakarta.enterprise.context.RequestScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequestScoped
public class VendorAddView extends AbstractAddView {

    @Autowired
    VendorController VendorController;

    private String name;

    private Region region;

    @Autowired
    private RegionController regionController;

    public void addVendor() {
        Vendor vendor = new Vendor();

        if (name.isEmpty()) {
            vendor.setName("UNNAMED");
        } else {
            vendor.setName(name);
        }

        vendor.setRegion(region);
        VendorController.saveVendor(vendor);
    }

    @Override
    public String getOutputString() {
        return "Vendor %s added".formatted(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}