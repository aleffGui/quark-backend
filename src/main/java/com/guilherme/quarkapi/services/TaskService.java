package com.guilherme.quarkapi.services;

import org.springframework.stereotype.Service;

import com.guilherme.quarkapi.dtos.NewTaskDTO;
import com.guilherme.quarkapi.models.Task;
import com.guilherme.quarkapi.models.User;
import com.guilherme.quarkapi.repositories.TaskRepository;

@Service
public class TaskService {
	
	private TaskRepository taskRepository;
	private UserService userService;
	
	private TaskService(TaskRepository taskRepository, UserService userService) {
		this.taskRepository = taskRepository;
		this.userService = userService;
	}
	
	public Task insert(Task task) {
		return taskRepository.save(task);
	}
	
	public Task findById(Long id) {
		Task task = this.taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada."));
		return task;
	}
	
	public Task fromDTO(NewTaskDTO taskDto) {
		Task task = new Task(taskDto.getTitle(), taskDto.getDescription(), taskDto.getPriority(), taskDto.getDeadline(), false);
		
		User userObj = userService.findById(taskDto.getUserId());
		task.setResponsible(userObj);
		
		return task;
	}
}
