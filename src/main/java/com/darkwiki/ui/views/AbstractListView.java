package com.darkwiki.ui.views;

import java.util.Collection;

public abstract class AbstractListView<T> {

    private Collection<T> collection;

    public Collection<T> listAll() {
        return collection;
    }

    abstract public void init();

    public Collection<T> getCollection() {
        return collection;
    }

    public void setCollection(Collection<T> collection) {
        this.collection = collection;
    }
}