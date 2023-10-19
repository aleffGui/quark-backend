package com.guilherme.quarkapi.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.guilherme.quarkapi.dtos.NewUserDTO;
import com.guilherme.quarkapi.dtos.UserDTO;
import com.guilherme.quarkapi.dtos.UserFilterDTO;
import com.guilherme.quarkapi.enums.UserRole;
import com.guilherme.quarkapi.models.User;
import com.guilherme.quarkapi.services.UserService;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	UserService service;
	
	@GetMapping("/paginated")
    public ResponseEntity<Page<UserDTO>> findAllPaginated(
    		@RequestParam(value = "paginate", required = false, defaultValue = "0") Boolean paginate,
    		@RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "userName", required = false) String userName,
            @RequestParam(value = "firstNameOrLastName", required = false) String firstNameOrLastName,
            @RequestParam(value = "role", required = false) UserRole role) {
        
    	UserFilterDTO filter = new UserFilterDTO(id, firstNameOrLastName, userName, role);

        Page<UserDTO> users = service.findAllPaginated(filter, page);
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
		UserDTO user = service.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
			List<UserDTO> users = service.findAll();
			return ResponseEntity.status(HttpStatus.OK).body(users);
	}
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody NewUserDTO user) {
		var obj = service.insert(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody NewUserDTO newUser) {
		User user = service.fromDTO(newUser);
		service.update(id, user);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}
