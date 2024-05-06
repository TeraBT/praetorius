package com.darkwiki.ui.views;

import com.darkwiki.controllers.RegionController;
import com.darkwiki.model.Region;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequestScoped
public class RegionListView {

    @Autowired
    RegionController regionController;

    Collection<Region> regionCollection;

    public Collection<Region> listAllRegions() {
        return regionCollection;
    }

    @PostConstruct
    public void init() {
        regionCollection = regionController.getAllRegions();
    }

}