package com.guilherme.quarkapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilherme.quarkapi.models.User;
import com.guilherme.quarkapi.repositories.UserRepository;
import com.guilherme.quarkapi.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User findById(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado."));
		return user;
	}
}
