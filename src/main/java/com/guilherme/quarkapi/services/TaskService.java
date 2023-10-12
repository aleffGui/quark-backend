package com.guilherme.quarkapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilherme.quarkapi.dtos.TaskRegisterDTO;
import com.guilherme.quarkapi.models.Task;
import com.guilherme.quarkapi.models.User;
import com.guilherme.quarkapi.repositories.TaskRepository;
import com.guilherme.quarkapi.repositories.UserRepository;

@Service
public class TaskService {

	@Autowired
	TaskRepository taskRespository;
	
	@Autowired
	UserRepository userRepository;
	
	
	public Task registerTask(Task task) {
		return taskRespository.save(task);
	}
	
	public Task fromDTO(TaskRegisterDTO taskDto) {
		Task task = new Task(taskDto.getTitle(), taskDto.getDescription(), taskDto.getPriority(), taskDto.getDeadline(), false);
		
		User userObj = userRepository.findById(taskDto.getUserId()).orElseThrow(() -> new RuntimeException("Responsável não encontrado."));
		task.setResponsible(userObj);
		
		return task;
	}
}
