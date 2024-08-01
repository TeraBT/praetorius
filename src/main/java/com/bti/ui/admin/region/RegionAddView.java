package com.bti.ui.admin.region;

import com.bti.controllers.RegionController;
import com.bti.model.Region;
import com.bti.ui.admin.AbstractAddView;
import jakarta.enterprise.context.RequestScoped;
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