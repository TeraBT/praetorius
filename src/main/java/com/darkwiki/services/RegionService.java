package com.darkwiki.services;

import com.darkwiki.model.Region;
import com.darkwiki.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class RegionService {

    @Autowired
    RegionRepository regionRepository;

    public Collection<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    public Region saveRegion(String name) {
        Region region = new Region();
        region.setName(name);
        regionRepository.save(region);
        return region;
    }

    public Region saveRegion(Region order) {
        regionRepository.save(order);
        return order;
    }
}
