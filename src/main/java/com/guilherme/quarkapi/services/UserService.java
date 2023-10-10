package com.guilherme.quarkapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilherme.quarkapi.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
}
