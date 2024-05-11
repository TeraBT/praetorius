package com.darkwiki.ui.views;

import org.primefaces.event.RowEditEvent;

import java.util.Collection;

public abstract class AbstractListEditView<T> {

    private Collection<T> collection;

    public Collection<T> listAllVendors() {
        return collection;
    }

    abstract public void onRowEdit(RowEditEvent<T> event);

    abstract public void onRowCancel(RowEditEvent<T> event);

    abstract public void init();

    public Collection<T> getCollection() {
        return collection;
    }

    public void setCollection(Collection<T> collection) {
        this.collection = collection;
    }
}