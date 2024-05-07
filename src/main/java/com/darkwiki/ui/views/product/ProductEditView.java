package com.darkwiki.ui.views.product;

import com.darkwiki.controllers.OrderController;
import com.darkwiki.model.Order;
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
public class ProductEditView {

    @Autowired
    OrderController orderController;

    Collection<Order> orderCollection;

    @PostConstruct
    public void init() {
        orderCollection = orderController.getAllOrders();
    }

    public Collection<Order> listAllOrders() {
        return orderCollection;
    }

    public void onRowEdit(RowEditEvent<Order> event) {
        orderController.saveOrder(event.getObject());
        FacesMessage msg = new FacesMessage("Order Edited", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent<Order> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
