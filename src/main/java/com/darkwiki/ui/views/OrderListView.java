package com.darkwiki.ui.views;

import com.darkwiki.entities.Order;
import com.darkwiki.ui.controllers.OrderController;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequestScoped
public class OrderListView {

    @Autowired
    OrderController orderController;

    Collection<Order> orderCollection;

    public Collection<Order> listAllOrders() {
        return orderCollection;
    }

    @PostConstruct
    public void init() {
        orderCollection = orderController.getAllOrders();
    }

}