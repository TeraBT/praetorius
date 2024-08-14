package com.bti.ui.vendor;

import com.bti.auxiliaries.SessionInfoService;
import com.bti.controllers.OrderController;
import com.bti.model.Order;
import com.bti.model.Role;
import com.bti.services.OrderService;
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
public class VendorOrderManagementView extends AbstractListEditView<Order> {

    @Autowired
    private OrderController orderController;

    @Autowired
    private OrderService orderService;

    @Autowired
    private VendorService vendorService;

    @Autowired
    private SessionInfoService sessionInfoService;

    @PostConstruct
    @Override
    public void init() {
        if (sessionInfoService.hasRole(Role.VENDOR.name())) {
            Long vendorId = vendorService.getVendorIdForUserIfAllowed(sessionInfoService.getCurrentUser()).orElseThrow();
            super.setCollection(orderService.getOrderCollectionByVendorId(vendorId));
        } else {
            super.setCollection(null);
        }
    }

    @Override
    public void onRowEdit(RowEditEvent<Order> event) {
        orderController.saveOrder(event.getObject());
        FacesMessage msg = new FacesMessage("Order Edited", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    @Override
    public void onRowCancel(RowEditEvent<Order> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
