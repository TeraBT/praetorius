package com.darkwiki.ui.views;

import com.darkwiki.controllers.VendorController;
import com.darkwiki.model.Vendor;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequestScoped
public class VendorAddView {

    @Autowired
    VendorController VendorController;

    private String name;

    public void addVendor() {
        Vendor vendor = new Vendor();

        if (name.isEmpty()) {
            vendor.setName("UNNAMED");
        } else {
            vendor.setName(name);
        }

        VendorController.saveVendor(vendor);
    }

    public void outputAddVendorSuccess() {
        FacesMessage msg = new FacesMessage("Vendor %s added".formatted(name));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}