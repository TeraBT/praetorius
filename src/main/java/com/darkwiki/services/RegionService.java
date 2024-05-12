package com.darkwiki.services;

import com.darkwiki.model.Order;
import com.darkwiki.model.Region;
import com.darkwiki.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RegionService {

    @Autowired
    RegionRepository regionRepository;

    public Collection<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    public Collection<String> getAllRegionNames() {
        return getAllRegions().stream().map(Region::getName).collect(Collectors.toSet());
    }

    public Region saveRegion(Region region) {
        regionRepository.save(region);
        return region;
    }

    public Region saveRegion(String name) {
        Region region = new Region();
        region.setName(name);
        regionRepository.save(region);
        return region;
    }


    public Optional<Region> getRegion(long id) {
        return regionRepository.findById(id);
    }

    public Optional<Region> getRegion(String name) {
        return regionRepository.findByName(name);
    }

    public boolean deleteRegion(Long id) {

        boolean wasPresent = regionRepository.findById(id).isPresent();

        if (wasPresent) {
            regionRepository.deleteById(id);
        }

        return wasPresent;
    }
}
