package com.guilherme.quarkapi.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilherme.quarkapi.enums.UserRole;
import com.guilherme.quarkapi.models.Role;
import com.guilherme.quarkapi.models.User;
import com.guilherme.quarkapi.repositories.RoleRepository;
import com.guilherme.quarkapi.repositories.TaskRepository;
import com.guilherme.quarkapi.repositories.UserRepository;

import jakarta.annotation.PostConstruct;

@Service
public class DBService {

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	TaskRepository taskRepository;
	
	@Autowired
	UserRepository userRepository;
	
	
	@PostConstruct
	public void initializeDatabase() {
		
		if(roleRepository.count()  == 0 && userRepository.count() == 0) {
			Role roleUser = new Role(UserRole.ROLE_USER);
			Role roleAdmin = new Role(UserRole.ROLE_ADMIN);
			
			roleRepository.saveAll(List.of(roleUser, roleAdmin));
			
			User user1 = new User("Guilherme", "Ferreira", "gui@teste.com", "123456");
			user1.setRoles(Set.of(roleAdmin));
			
			User user2 = new User("Fábio", "Ferreira", "fabio@teste.com", "123456");
			user2.setRoles(Set.of(roleUser));
			
			User user3 = new User("Tiago", "Ferreira", "tiago@teste.com", "123456");
			user3.setRoles(Set.of(roleUser));
			
			User user4 = new User("José", "Ferreira", "jose@teste.com", "123456");
			user4.setRoles(Set.of(roleUser));
			
			User user5 = new User("Evanir", "Ferreira", "vane@teste.com", "123456");
			user5.setRoles(Set.of(roleUser));
			
			User user6 = new User("Bruna", "Ferreira", "bruna@teste.com", "123456");
			user6.setRoles(Set.of(roleUser));
			
			userRepository.saveAll(List.of(user1, user2, user3, user4, user5, user6));
			
		}
		
	}
}
