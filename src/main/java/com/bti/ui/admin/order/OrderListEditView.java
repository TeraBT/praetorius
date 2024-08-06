package com.bti.ui.admin.order;

import com.bti.model.Order;
import com.bti.controllers.OrderController;
import com.bti.model.OrderStatus;
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
public class OrderListEditView extends AbstractListEditView<Order> {

    @Autowired
    OrderController orderController;

    private final OrderStatus[] orderStatusArray = OrderStatus.values();

    @PostConstruct
    @Override
    public void init() {
        super.setCollection(orderController.getAllOrders());
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

    public OrderStatus[] getOrderStatusArray() {
        return orderStatusArray;
    }
}