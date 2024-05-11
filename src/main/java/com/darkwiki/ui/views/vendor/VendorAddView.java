package com.darkwiki.ui.views.vendor;

import com.darkwiki.controllers.RegionController;
import com.darkwiki.controllers.VendorController;
import com.darkwiki.model.Region;
import com.darkwiki.model.Vendor;
import com.darkwiki.ui.views.AbstractAddView;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

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