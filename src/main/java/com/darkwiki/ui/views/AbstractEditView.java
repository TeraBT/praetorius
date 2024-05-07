package com.darkwiki.ui.views;

import com.darkwiki.controllers.OrderController;
import com.darkwiki.model.Order;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

public abstract class AbstractEditView<T> {

    Collection<T> collection;

    abstract public void init();

    public Collection<T> listAll() {
        return collection;
    }

    abstract public void onRowEdit(RowEditEvent<T> event);

    abstract public void onRowCancel(RowEditEvent<T> event);

}
