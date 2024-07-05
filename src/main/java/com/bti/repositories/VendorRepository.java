package com.bti.repositories;

import com.bti.model.Vendor;

import java.util.Optional;

public interface VendorRepository extends AbstractRepository<Vendor, Long> {
    Optional<Vendor> findByName(String name);

}
