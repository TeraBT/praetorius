package com.bti.ui.views.product;

import com.bti.controllers.OrderController;
import com.bti.model.Product;
import com.bti.services.ProductService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("request")
public class ProductView {

    @Autowired
    private OrderController orderController;

    @PostConstruct
    public void init() {
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        this.product = (Product) httpSession.getAttribute("product");
    }

    private Product product;

    private Integer amount = 0;

    private String comment;

    public void placeOrder(Product product, Integer amount, String comment) {
        orderController.placeOrder(product, amount, comment);
        FacesMessage msg = new FacesMessage("Order Placed.",
                "'%s' has been ordered successfully.".formatted(product.getName()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
