package com.darkwiki.ui.views;

import com.darkwiki.controllers.RegionController;
import com.darkwiki.model.Region;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequestScoped
public class RegionAddView {

    @Autowired
    RegionController regionController;

    private String name;

    public void addRegion() {
        Region region = new Region();

        if (name.isEmpty()) {
            region.setName("UNNAMED");
        } else {
            region.setName(name);
        }

        regionController.saveRegion(region);
    }

    public void outputAddRegionSuccess() {
        FacesMessage msg = new FacesMessage("Region %s added".formatted(name));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}