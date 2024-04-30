package com.darkwiki.repositories;

import com.darkwiki.entities.Vendor;

import java.util.Optional;

public interface VendorRepository extends AbstractRepository<Vendor, Long> {
    Optional<Vendor> findById(long id);

}
