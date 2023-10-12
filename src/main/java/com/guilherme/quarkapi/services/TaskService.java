package com.guilherme.quarkapi.services;

import org.springframework.stereotype.Service;

import com.guilherme.quarkapi.dtos.NewTaskDTO;
import com.guilherme.quarkapi.models.Task;
import com.guilherme.quarkapi.models.User;
import com.guilherme.quarkapi.repositories.TaskRepository;

@Service
public class TaskService {
	
	private TaskRepository taskRespository;
	private UserService userService;
	
	private TaskService(TaskRepository taskRepository, UserService userService) {
		this.taskRespository = taskRepository;
		this.userService = userService;
	}
	
	public Task insert(Task task) {
		return taskRespository.save(task);
	}
	
	public Task fromDTO(NewTaskDTO taskDto) {
		Task task = new Task(taskDto.getTitle(), taskDto.getDescription(), taskDto.getPriority(), taskDto.getDeadline(), false);
		
		User userObj = userService.findById(taskDto.getUserId());
		task.setResponsible(userObj);
		
		return task;
	}
}
