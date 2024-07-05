package com.bti.repositories;

import com.bti.model.Region;

import java.util.Optional;

public interface RegionRepository extends AbstractRepository<Region, Long> {

    Optional<Region> findByName(String name);
}
