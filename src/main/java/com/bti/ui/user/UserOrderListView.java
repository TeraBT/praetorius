package com.bti.ui.user;

import com.bti.auxiliaries.SessionInfoService;
import com.bti.model.Order;
import com.bti.services.OrderService;
import com.bti.ui.admin.AbstractListEditView;
import jakarta.annotation.PostConstruct;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
@Scope("request")
public class UserOrderListView extends AbstractListEditView<Order> {

    @Autowired
    private OrderService orderService;

    @Autowired
    private SessionInfoService sessionInfoService;

    @PostConstruct
    @Override
    public void init() {
        if (sessionInfoService.isLoggedIn()) {
            super.setCollection(orderService.getOrderCollectionByBuyerId(sessionInfoService.getCurrentUser().getId()).stream().sorted(
                    Comparator.comparing(Order::getCreateTimestamp, Comparator.reverseOrder())).toList());
        } else {
            super.setCollection(null);
        }
    }

    @Override
    public void onRowEdit(RowEditEvent<Order> event) {

    }

    @Override
    public void onRowCancel(RowEditEvent<Order> event) {

    }
}
