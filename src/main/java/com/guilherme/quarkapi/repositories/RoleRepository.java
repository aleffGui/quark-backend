package com.guilherme.quarkapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guilherme.quarkapi.enums.UserRole;
import com.guilherme.quarkapi.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	public Optional<Role> findByName(UserRole name);
}
