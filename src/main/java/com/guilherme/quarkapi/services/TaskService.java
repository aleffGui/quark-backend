package com.guilherme.quarkapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilherme.quarkapi.repositories.TaskRepository;

@Service
public class TaskService {

	@Autowired
	TaskRepository taskRespository;
}
