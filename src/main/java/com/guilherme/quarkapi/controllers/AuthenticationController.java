package com.guilherme.quarkapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guilherme.quarkapi.dtos.AuthenticationDTO;
import com.guilherme.quarkapi.dtos.LoginResponseTokenDTO;
import com.guilherme.quarkapi.models.User;
import com.guilherme.quarkapi.repositories.UserRepository;
import com.guilherme.quarkapi.services.TokenService;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("auth")
public class AuthenticationController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TokenService tokenService;
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseTokenDTO> login(@Valid @RequestBody AuthenticationDTO data) {
		var userNamePassword = new UsernamePasswordAuthenticationToken(data.getUserName(), data.getPassword());
		var auth = this.authenticationManager.authenticate(userNamePassword);
		var token = tokenService.generateToken((User) auth.getPrincipal());
		return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseTokenDTO(token));
	}
}
