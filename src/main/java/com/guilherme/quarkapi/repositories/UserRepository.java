package com.guilherme.quarkapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guilherme.quarkapi.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
