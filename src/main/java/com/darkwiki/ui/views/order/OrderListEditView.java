package com.darkwiki.ui.views.order;

import com.darkwiki.model.Order;
import com.darkwiki.controllers.OrderController;
import com.darkwiki.ui.views.AbstractListEditView;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequestScoped
public class OrderListEditView extends AbstractListEditView<Order> {

    @Autowired
    OrderController orderController;

    @PostConstruct
    public void init() {
        super.setCollection(orderController.getAllOrders());
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