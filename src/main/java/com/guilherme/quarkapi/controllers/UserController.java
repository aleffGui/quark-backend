package com.guilherme.quarkapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.guilherme.quarkapi.services.UserService;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	UserService service;
	
}
