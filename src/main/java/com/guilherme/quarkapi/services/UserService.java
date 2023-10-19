package com.guilherme.quarkapi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.guilherme.quarkapi.dtos.NewUserDTO;
import com.guilherme.quarkapi.dtos.UserDTO;
import com.guilherme.quarkapi.dtos.UserFilterDTO;
import com.guilherme.quarkapi.models.User;
import com.guilherme.quarkapi.repositories.UserRepository;
import com.guilherme.quarkapi.services.exceptions.AlreadyRegisteredUserException;
import com.guilherme.quarkapi.services.exceptions.DataIntegrityViolationException;
import com.guilherme.quarkapi.services.exceptions.ObjectNotFoundException;
import com.guilherme.quarkapi.specifications.UserSpecifications;

@Service
public class UserService {

	private UserRepository userRepository;
	private TaskService taskService;
	
	public UserService(UserRepository userRepository, TaskService taskService) {
		this.userRepository = userRepository;
		this.taskService = taskService;
	}
	public UserDTO findById(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado."));
		return toDTO(user);
	}
	
	private User findByIdAux(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado."));
		return user;
	}
	public Page<UserDTO> findAllPaginated(UserFilterDTO userFilter, int page) {
		Pageable pageable = PageRequest.of(page, 20, Sort.by(Sort.Order.desc("id")));
        Specification<User> spec = UserSpecifications.withFilters(userFilter);
        Page<User> users = userRepository.findAll(spec, pageable);
        return users.map(task -> toDTO(task));
	}
	
	public List<UserDTO> findAll() {
	    List<User> users = userRepository.findAll();

	    List<UserDTO> userDTOs = users.stream()
	            .map(this::toDTO)
	            .collect(Collectors.toList());

	    return userDTOs;
	}

	public UserDTO insert(NewUserDTO userDto) {
		if(userRepository.existsByUserName(userDto.getUserName())) {
			throw new AlreadyRegisteredUserException("Username já está sendo utilizado");
		}
		
		String encryptPassord = new BCryptPasswordEncoder().encode(userDto.getPassword());
		
		var newUser = new User(userDto.getUserName(), userDto.getFirstName(), userDto.getLastName(), encryptPassord, userDto.getRole());
		User user = this.userRepository.save(newUser);
		return toDTO(user);
	}
	
	public void update(Long id, User newUser) {
		User user = findByIdAux(id);
		this.set(user, newUser);
		this.userRepository.save(user);
	}
	
	private void set(User user, User newUser) {
		String password = "";
		if(newUser.getUsername() != null) {
			if(userRepository.findByUserNameAndIdNot(newUser.getUserName(), user.getId()) != null) {
				throw new AlreadyRegisteredUserException("Username já está sendo utilizado");
			}	
		}
		if(newUser.getPassword() != null && !newUser.getPassword().isEmpty()) {
			String encryptPassword = new BCryptPasswordEncoder().encode(newUser.getPassword());
			password = encryptPassword;
		} else {
			password = user.getPassword();
		}
		user.setFirstName(newUser.getFirstName());
		user.setLastName(newUser.getLastName());
		user.setRole(newUser.getRole());
		user.setPassword(password);
	}
	
	public void delete(Long id) {
		User user = findByIdAux(id);
		if(!taskService.findByUserId(user.getId()).isEmpty()) {
			throw new DataIntegrityViolationException("Usuário possui tarefas vinculadas");
		}
		this.userRepository.deleteById(id);
	}
	
	private UserDTO toDTO(User user) {
		return new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getUserName(), user.getRole());
	}
	
	public User fromDTO(NewUserDTO userDto) {
		User user = new User(userDto.getUserName(), userDto.getFirstName(), userDto.getLastName(), userDto.getPassword(), userDto.getRole());
		
		return user;
	}
}
