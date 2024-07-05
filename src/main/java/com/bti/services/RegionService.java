package com.bti.services;

import com.bti.model.Region;
import com.bti.repositories.RegionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
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

    @Transactional
    public boolean deleteRegion(Long id) {

        Optional<Region> region = regionRepository.findById(id);

        if (region.isPresent()) {

            Set.copyOf(region.get().getVendorSet()).forEach(region.get()::removeFromVendorSet);
            regionRepository.deleteById(id);

            return true;
        }

        return false;
    }

    public Optional<Region> findRegionById(Long id) {
        return regionRepository.findById(id);
    }
}
