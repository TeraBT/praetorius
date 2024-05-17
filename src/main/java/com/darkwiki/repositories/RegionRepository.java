package com.darkwiki.repositories;

import com.darkwiki.model.Region;

import java.util.Collection;
import java.util.Optional;

public interface RegionRepository extends AbstractRepository<Region, Long> {

    Optional<Region> findByName(String name);
}
