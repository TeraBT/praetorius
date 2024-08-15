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

import java.util.Collection;

@Component
@Scope("request")
public class VendorGeneralManagementView {

    @Autowired
    private ProductController productController;

    @Autowired
    private ProductService productService;

    @Autowired
    private VendorService vendorService;

    @Autowired
    private SessionInfoService sessionInfoService;

    private Vendor vendor;

    private Collection<Product> productCollection;

    @PostConstruct
    public void init() {
        if (sessionInfoService.hasRole(Role.VENDOR.name())) {
            Long vendorId = vendorService.getVendorIdForUserIfAllowed(sessionInfoService.getCurrentUser()).orElseThrow();
            productCollection = productService.getProductCollectionByVendorIdSortedByName(vendorId);
            vendor = vendorService.getVendor(vendorId).orElseThrow();
        } else {
            productCollection = null;
        }
    }

    // TODO: Adapt add product functionality from the vendor's perspective



    public Vendor getVendor() {
        return vendor;
    }

    public Collection<Product> getProductCollection() {
        return productCollection;
    }
}
