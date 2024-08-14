package com.bti.ui.vendor;

import com.bti.auxiliaries.SessionInfoService;
import com.bti.controllers.ProductController;
import com.bti.model.Product;
import com.bti.model.Role;
import com.bti.model.Vendor;
import com.bti.services.ProductService;
import com.bti.services.VendorService;
import com.bti.ui.admin.AbstractListEditView;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("request")
public class VendorGeneralManagementView extends AbstractListEditView<Product> {

    @Autowired
    private ProductController productController;

    @Autowired
    private ProductService productService;

    @Autowired
    private VendorService vendorService;

    @Autowired
    private SessionInfoService sessionInfoService;

    private Vendor vendor;

    @PostConstruct
    @Override
    public void init() {
        if (sessionInfoService.hasRole(Role.VENDOR.name())) {
            Long vendorId = vendorService.getVendorIdForUserIfAllowed(sessionInfoService.getCurrentUser()).orElseThrow();
            super.setCollection(productService.getProductCollectionByVendorId(vendorId));
            vendor = vendorService.getVendor(vendorId).orElseThrow();
        } else {
            super.setCollection(null);
        }
    }

    // TODO: Adapt add vendor functionality from the vendor's perspective

    @Override
    public void onRowEdit(RowEditEvent<Product> event) {
        productController.saveProduct(event.getObject());
        FacesMessage msg = new FacesMessage("Product Edited", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    @Override
    public void onRowCancel(RowEditEvent<Product> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public Vendor getVendor() {
        return vendor;
    }
}
