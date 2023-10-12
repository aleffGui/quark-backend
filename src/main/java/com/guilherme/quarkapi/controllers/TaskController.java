package com.guilherme.quarkapi.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.guilherme.quarkapi.dtos.NewTaskDTO;
import com.guilherme.quarkapi.dtos.TaskDTO;
import com.guilherme.quarkapi.models.Task;
import com.guilherme.quarkapi.services.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("tasks")
public class TaskController {

	@Autowired
	TaskService taskService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Task> findById(@PathVariable Long id) {
		Task task = taskService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(task);
	}
	
	@GetMapping
	public ResponseEntity<Page<TaskDTO>> findAll(Pageable pageable) {
		Page<TaskDTO> tasks = taskService.findAll(pageable);
		return ResponseEntity.status(HttpStatus.OK).body(tasks);
	}

	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody NewTaskDTO taskDto) {
		Task task = taskService.fromDTO(taskDto);
		task = taskService.insert(task);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(task.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TaskDTO> update(@PathVariable Long id, @RequestBody NewTaskDTO newTask) {
		Task task = taskService.fromDTO(newTask);
		TaskDTO taskDto = taskService.update(id, task);
		return ResponseEntity.status(HttpStatus.OK).body(taskDto);
	}
}
