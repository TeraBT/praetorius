package com.bti.repositories;

import com.bti.model.Role;

import java.util.Optional;

public interface RoleRepository extends AbstractRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
