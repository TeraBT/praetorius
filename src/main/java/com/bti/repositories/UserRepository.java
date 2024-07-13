package com.bti.repositories;

import com.bti.model.User;

import java.util.Optional;

public interface UserRepository extends AbstractRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
