package com.guilherme.quarkapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.guilherme.quarkapi.enums.UserRole;
import com.guilherme.quarkapi.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

	public UserDetails findByUserName(String userName);
	
	public boolean existsByUserName(String userName);
	
	public UserDetails findByUserNameAndIdNot(String userName, Long id);
	
	public List<User> findByRole(UserRole role);
}
