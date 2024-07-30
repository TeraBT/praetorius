package com.bti.ui.views.product;

import com.bti.controllers.OrderController;
import com.bti.model.Product;
import com.bti.services.ProductService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@Scope
@Controller
public class ProductView {

    @Autowired
    private OrderController orderController;

    private Product product;

    private Integer amount = 0;

    private String ent;

    @Autowired
    private ProductService productService;

    public String getEnt() {
        return ent;
    }

    public void setEnt(String ent) {
        this.ent = ent;
    }

    public void print(String string) {
        System.out.println(string);
    }

    @GetMapping("/product/{productId}")
    public String initProductView(@PathVariable long productId) {
        System.out.println("Product ID: " + productId);
        this.product = productService.getProduct(productId).orElse(null);
        System.out.println(product);
        return "product";
    }

    private List<String> entList = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");

    public List<String> getEntList() {
        return entList;
    }

    public void setEntList(List<String> entList) {
        this.entList = entList;
    }

    private String comment;

    public void placeOrder(Product product, Integer amount, String comment) { // TODO: Amount not set, probably a frontend converter issue.
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
