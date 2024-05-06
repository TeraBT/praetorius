package com.darkwiki.controllers;

import com.darkwiki.model.Region;
import com.darkwiki.services.OrderService;
import com.darkwiki.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Collection;

@Controller
public class RegionController {

    @Autowired
    RegionService regionService;

    public Collection<Region> getAllRegions() {
        return regionService.getAllRegions();
    }

    public void saveRegion(String name) {
        regionService.saveRegion(name);
    }

    public void saveRegion(Region region) {
        regionService.saveRegion(region);
    }
}
