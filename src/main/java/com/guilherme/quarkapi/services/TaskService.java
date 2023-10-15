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
import com.guilherme.quarkapi.repositories.UserRepository;
import com.guilherme.quarkapi.services.exceptions.ObjectNotFoundException;
import com.guilherme.quarkapi.services.exceptions.TaskCompletedException;

@Service
public class TaskService {
	
	private TaskRepository taskRepository;
	private UserRepository userRepository;
	
	private TaskService(TaskRepository taskRepository, UserRepository userRepository) {
		this.taskRepository = taskRepository;
		this.userRepository = userRepository;
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
	public void delete(Long id) {
		Task task = findById(id);
		taskRepository.delete(task);
	}
	private void set(Task task, Task newTask) {
		
		if(newTask.getResponsible() != null) {
			User user = userRepository.findById(newTask.getResponsible().getId()).orElseThrow(() -> new ObjectNotFoundException("Responsável não encontrado."));;			
			task.setResponsible(user);
		}
		task.setTitle(newTask.getTitle());
		task.setDescription(newTask.getDescription());
		task.setPriority(newTask.getPriority());
		task.setDeadline(newTask.getDeadline());
	}
	public Task findById(Long id) {
		Task task = this.taskRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Tarefa não encontrada! Id: " + id));
		return task;
	}
	
	public TaskDTO findByIdToDto(Long id) {
		Task task = findById(id);
		return toDTO(task);
	}
	
	public Page<TaskDTO> findAll(Pageable pageable) {
	    Page<Task> tasks = this.taskRepository.findAll(pageable);  
	    return tasks.map(task -> toDTO(task));
	}
	
	private TaskDTO toDTO(Task task) {
		UserDTO userDto = new UserDTO(task.getResponsible().getId(), task.getResponsible().getFirstName(), task.getResponsible().getLastName());
	    return new TaskDTO(task.getId(), task.getTitle(), task.getDescription(), task.getStatus(), task.getPriority().getValue(), task.getDeadline(), userDto);
	}

	public Task fromDTO(NewTaskDTO taskDto) {
		Task task = new Task(taskDto.getTitle(), taskDto.getDescription(), taskDto.getPriority(), taskDto.getDeadline(), false);
		
		User userObj = userRepository.findById(taskDto.getUserId()).orElseThrow(() -> new ObjectNotFoundException("Responsável não encontrado."));
		task.setResponsible(userObj);
		
		return task;
	}
	
	public void markTaskAsComplete(Long id) throws Exception {
		Task task = findById(id);
		if(task.getStatus()) {
			throw new TaskCompletedException("Tarefa com o Id: " + id + " já consta como concluída.");
		}
		taskRepository.markAsComplete(task.getId());
	}
}
