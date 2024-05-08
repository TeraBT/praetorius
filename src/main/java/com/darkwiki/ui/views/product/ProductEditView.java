package com.darkwiki.ui.views.product;

import com.darkwiki.controllers.ProductController;
import com.darkwiki.model.Product;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequestScoped
public class ProductEditView {

    @Autowired
    ProductController productController;

    Collection<Product> productCollection;

    @PostConstruct
    public void init() {
        productCollection = productController.getAllProducts();
    }

    public Collection<Product> listAllProducts() {
        return productCollection;
    }

    public void onRowEdit(RowEditEvent<Product> event) {
        productController.saveProduct(event.getObject());
        FacesMessage msg = new FacesMessage("Product Edited", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent<Product> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
