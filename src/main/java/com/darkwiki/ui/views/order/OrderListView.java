package com.darkwiki.ui.views.order;

import com.darkwiki.model.Order;
import com.darkwiki.controllers.OrderController;
import com.darkwiki.ui.views.AbstractListView;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testcontainers.shaded.org.checkerframework.checker.units.qual.A;

import java.util.Collection;

@Component
@RequestScoped
public class OrderListView extends AbstractListView<Order> {

    @Autowired
    OrderController orderController;

    @PostConstruct
    public void init() {
        super.setCollection(orderController.getAllOrders());
    }

}