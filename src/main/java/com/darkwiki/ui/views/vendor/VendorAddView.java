package com.darkwiki.ui.views.vendor;

import com.darkwiki.controllers.VendorController;
import com.darkwiki.model.Region;
import com.darkwiki.model.Vendor;
import com.darkwiki.ui.views.AbstractAddView;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequestScoped
public class VendorAddView extends AbstractAddView {

    @Autowired
    VendorController VendorController;

    private String name;

    private Region region;

    public void addVendor() {
        Vendor vendor = new Vendor();

        if (name.isEmpty()) {
            vendor.setName("UNNAMED");
        } else {
            vendor.setName(name);
        }

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