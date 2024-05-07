package com.darkwiki.ui.views.region;

import com.darkwiki.controllers.RegionController;
import com.darkwiki.model.Region;
import com.darkwiki.ui.views.AbstractListView;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequestScoped
public class RegionListView extends AbstractListView<Region> {

    @Autowired
    RegionController regionController;

    @PostConstruct
    public void init() {
        super.setCollection(regionController.getAllRegions());
    }

}