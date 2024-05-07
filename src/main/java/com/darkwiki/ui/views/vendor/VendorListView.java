package com.darkwiki.ui.views.vendor;

import com.darkwiki.controllers.VendorController;
import com.darkwiki.model.Vendor;
import com.darkwiki.ui.views.AbstractListView;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequestScoped
public class VendorListView extends AbstractListView<Vendor> {

    @Autowired
    VendorController VendorController;

    @PostConstruct
    public void init() {super.setCollection(VendorController.getAllVendors());}
}