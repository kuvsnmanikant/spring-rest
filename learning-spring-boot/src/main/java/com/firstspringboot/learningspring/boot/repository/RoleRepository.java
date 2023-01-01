package com.firstspringboot.learningspring.boot.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.firstspringboot.learningspring.boot.entries.Roles;

public interface RoleRepository extends JpaRepository<Roles, Long> {

    Optional<Roles> findByName(String name);
}
