package com.guilherme.quarkapi.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.guilherme.quarkapi.dtos.NewTaskDTO;
import com.guilherme.quarkapi.dtos.TaskDTO;
import com.guilherme.quarkapi.dtos.TaskFilterDTO;
import com.guilherme.quarkapi.enums.TaskPriority;
import com.guilherme.quarkapi.models.Task;
import com.guilherme.quarkapi.services.TaskService;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("tasks")
public class TaskController {

	@Autowired
	TaskService taskService;
	
	@GetMapping("/{id}")
	public ResponseEntity<TaskDTO> findById(@PathVariable Long id) {
		TaskDTO taskDto = taskService.findByIdToDto(id);
		return ResponseEntity.status(HttpStatus.OK).body(taskDto);
	}
	
    @GetMapping
    public ResponseEntity<Page<TaskDTO>> findAll(
    		@RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "titleOrDescription", required = false) String titleOrDescription,
            @RequestParam(value = "userId", required = false) Long userId,
            @RequestParam(value = "priority", required = false) TaskPriority priority,
            @RequestParam(value = "status", required = false) Boolean status) {
        
    	TaskFilterDTO filter = new TaskFilterDTO(id, titleOrDescription, userId, priority, status);

        Page<TaskDTO> tasks = taskService.findAll(filter, page);
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
	
	@PatchMapping("/{id}/complete")
	public ResponseEntity<Void> markTaskAsComplete(@PathVariable Long id) throws Exception {
		taskService.markTaskAsComplete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		taskService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
