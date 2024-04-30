package com.darkwiki.ui.controllers;

import com.darkwiki.entities.Order;
import com.darkwiki.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Collection;

@Controller
public class OrderController {

    @Autowired
    OrderService orderService;

    public Collection<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    public void saveOrder(String vendor, String product, double amount) {
        Order order = new Order();
        order.setVendor(vendor);
        order.setProduct(product);
        order.setAmount(amount);
        orderService.saveOrder(order);
    }
}
