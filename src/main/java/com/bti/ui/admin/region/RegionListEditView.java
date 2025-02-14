package com.bti.ui.admin.region;

import com.bti.controllers.RegionController;
import com.bti.model.Region;
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
public class RegionListEditView extends AbstractListEditView<Region> {

    @Autowired
    RegionController regionController;

    @PostConstruct
    public void init() {
        super.setCollection(regionController.getAllRegions());
    }


    public void onRowEdit(RowEditEvent<Region> event) {
        regionController.saveRegion(event.getObject());
        FacesMessage msg = new FacesMessage("Region Edited", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent<Region> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}