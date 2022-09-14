package com.dmilut.youtubeclone.repository;

import com.dmilut.youtubeclone.model.ERole;
import com.dmilut.youtubeclone.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
