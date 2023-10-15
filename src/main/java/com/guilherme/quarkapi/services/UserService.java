package com.guilherme.quarkapi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilherme.quarkapi.dtos.TaskDTO;
import com.guilherme.quarkapi.dtos.UserDTO;
import com.guilherme.quarkapi.models.Task;
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
	public List<UserDTO> findAll() {
		List<User> users = userRepository.findAll();
		List<UserDTO> usersDto = users.stream()
			.map(this::toDTO)
			.collect(Collectors.toList());
		
		return usersDto;
	}
	private UserDTO toDTO(User user) {
		return new UserDTO(user.getId(), user.getFirstName(), user.getLastName());
	}
}
