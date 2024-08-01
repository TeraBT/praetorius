package com.bti.ui.admin.product;

import com.bti.controllers.ProductController;
import com.bti.model.Product;
import com.bti.ui.admin.AbstractListEditView;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequestScoped
public class ProductListEditView extends AbstractListEditView<Product> {

    @Autowired
    ProductController productController;

    @PostConstruct
    public void init() {super.setCollection(productController.getAllProducts());}

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