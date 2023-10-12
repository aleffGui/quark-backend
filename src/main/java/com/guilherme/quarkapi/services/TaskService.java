package com.guilherme.quarkapi.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.guilherme.quarkapi.dtos.NewTaskDTO;
import com.guilherme.quarkapi.dtos.TaskDTO;
import com.guilherme.quarkapi.dtos.UserDTO;
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
	public TaskDTO update(Long id, Task newTask) {
		Task task = findById(id);

		this.set(task, newTask);
		task = taskRepository.save(task);
		return toDTO(task);
	}
	private void set(Task task, Task newTask) {
		
		if(newTask.getUser() != null) {
			User user = userService.findById(newTask.getUser().getId());			
			task.setResponsible(user);
		}
		task.setTitle(newTask.getTitle());
		task.setDescription(newTask.getDescription());
		task.setPriority(newTask.getPriority());
		task.setDeadline(newTask.getDeadline());
	}
	public Task findById(Long id) {
		Task task = this.taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada."));
		return task;
	}
	
	public Page<TaskDTO> findAll(Pageable pageable) {
	    Page<Task> tasks = this.taskRepository.findAll(pageable);  
	    return tasks.map(task -> toDTO(task));
	}
	
	private TaskDTO toDTO(Task task) {
		UserDTO userDto = new UserDTO(task.getUser().getId(), task.getUser().getFirstName(), task.getUser().getLastName());
	    return new TaskDTO(task.getId(), task.getTitle(), task.getDescription(), task.getStatus(), task.getPriority(), task.getDeadline(), userDto);
	}

	public Task fromDTO(NewTaskDTO taskDto) {
		Task task = new Task(taskDto.getTitle(), taskDto.getDescription(), taskDto.getPriority(), taskDto.getDeadline(), false);
		
		User userObj = userService.findById(taskDto.getUserId());
		task.setResponsible(userObj);
		
		return task;
	}
}
