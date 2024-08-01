package com.bti.ui.admin.producttype;

import com.bti.controllers.ProductTypeController;
import com.bti.model.ProductType;
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
public class ProductTypeListEditView extends AbstractListEditView<ProductType> {

    @Autowired
    ProductTypeController productTypeController;

    @PostConstruct
    public void init() {super.setCollection(productTypeController.getAllProductTypes());}

    public void onRowEdit(RowEditEvent<ProductType> event) {
        productTypeController.saveProductType(event.getObject());
        FacesMessage msg = new FacesMessage("ProductType Edited", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent<ProductType> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}