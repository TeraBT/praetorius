package com.bti.ui.views.vendor;

import com.bti.controllers.RegionController;
import com.bti.controllers.VendorController;
import com.bti.model.Region;
import com.bti.model.Vendor;
import com.bti.ui.views.AbstractListEditView;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequestScoped
public class VendorListEditView extends AbstractListEditView<Vendor> {

    @Autowired
    VendorController VendorController;

    @Autowired
    private VendorController vendorController;

    @Autowired
    private RegionController regionController;

    @PostConstruct
    public void init() {super.setCollection(VendorController.getAllVendors());}

    public Collection<Region> getAllRegions() {
        return regionController.getAllRegions();
    }

    public void onRowEdit(RowEditEvent<Vendor> event) {
        vendorController.saveVendor(event.getObject());
        FacesMessage msg = new FacesMessage("Vendor Edited", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent<Vendor> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}