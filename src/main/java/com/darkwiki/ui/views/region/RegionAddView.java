package com.darkwiki.ui.views.region;

import com.darkwiki.controllers.RegionController;
import com.darkwiki.model.Region;
import com.darkwiki.ui.views.AbstractAddView;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequestScoped
public class RegionAddView extends AbstractAddView {

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

    @Override
    public String getOutputString() {
        return "Region %s added".formatted(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}