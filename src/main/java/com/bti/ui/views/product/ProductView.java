package com.bti.ui.views.product;

import com.bti.controllers.OrderController;
import com.bti.model.Product;
import com.bti.services.ProductService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductView {

    @Autowired
    private OrderController orderController;

    private Product product;

    private int amount;

    private String comment;

    public void placeOrder(Product product, int amount, String comment) { // TODO: Amount not set, probably a frontend converter issue.
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
