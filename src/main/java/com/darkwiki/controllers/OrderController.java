package com.darkwiki.controllers;

import com.darkwiki.auxiliaries.OrderReferenceGenerator;
import com.darkwiki.model.Order;
import com.darkwiki.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;

@Controller
public class OrderController {

    @Autowired
    OrderService orderService;

    private final OrderReferenceGenerator orderReferenceGenerator = new OrderReferenceGenerator();

    public Collection<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    public void saveOrder(String vendorName, String productName, double amount) {
        Order order = new Order();
//        Vendor vendor = new Vendor();
//        vendor.setName(vendorName);
//        Product product = new Product();
        order.setCreateTimestamp(LocalDateTime.now(ZoneId.of("UTC+0")));
        order.setVendorName(vendorName);
        order.setProduct(productName);
        order.setAmount(amount);

        while (true) {
            String orderReference = orderReferenceGenerator.generateOrderReference();
            if (orderService.getOrderByOrderReference(orderReference).isEmpty()) {
                order.setOrderReference(orderReference);
                break;
            }
        }

        orderService.saveOrder(order);
    }

    public void saveOrder(Order order) {
        orderService.saveOrder(order);
    }
}
