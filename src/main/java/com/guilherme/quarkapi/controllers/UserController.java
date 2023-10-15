package com.guilherme.quarkapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guilherme.quarkapi.dtos.UserDTO;
import com.guilherme.quarkapi.services.UserService;

@CrossOrigin
@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	UserService service;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<UserDTO> usersDto = service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(usersDto);
	}
	
}
