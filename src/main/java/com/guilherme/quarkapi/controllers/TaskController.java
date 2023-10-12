package com.guilherme.quarkapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guilherme.quarkapi.dtos.TaskRegisterDTO;
import com.guilherme.quarkapi.models.Task;
import com.guilherme.quarkapi.services.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("tasks")
public class TaskController {

	@Autowired
	TaskService taskService;
	
	
	@PostMapping
	public ResponseEntity<Task> registerTask(@Valid @RequestBody TaskRegisterDTO taskDto) {
		Task task = taskService.fromDTO(taskDto);
		task = taskService.registerTask(task);
		return ResponseEntity.status(HttpStatus.CREATED).body(task);
	}
}
