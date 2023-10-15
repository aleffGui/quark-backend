package com.guilherme.quarkapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guilherme.quarkapi.dtos.AuthenticationDTO;
import com.guilherme.quarkapi.dtos.LoginResponseTokenDTO;
import com.guilherme.quarkapi.dtos.NewUserDTO;
import com.guilherme.quarkapi.models.User;
import com.guilherme.quarkapi.repositories.UserRepository;
import com.guilherme.quarkapi.services.TokenService;

import jakarta.validation.Valid;

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
	
	@PostMapping("/register")
	public ResponseEntity<Void> register(@Valid @RequestBody NewUserDTO data) {
		if(userRepository.findByUserName(data.getUserName()) != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
		String encryptPassord = new BCryptPasswordEncoder().encode(data.getPassword());
		
		var newUser = new User(data.getUserName(), data.getFirstName(), data.getLastName(), encryptPassord, data.getRole());
		this.userRepository.save(newUser);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
