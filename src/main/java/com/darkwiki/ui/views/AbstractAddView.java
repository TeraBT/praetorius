package com.darkwiki.ui.views;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

public abstract class AbstractAddView {

    public void outputAddSuccess() {
        FacesMessage msg = new FacesMessage(getOutputString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    abstract public String getOutputString();
}