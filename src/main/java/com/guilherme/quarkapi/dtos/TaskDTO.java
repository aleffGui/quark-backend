package com.guilherme.quarkapi.dtos;

import java.time.LocalDateTime;

import com.guilherme.quarkapi.enums.TaskPriority;

public class TaskDTO {

	private Long id;
	private String title;
	private String description;
	private Boolean status;
	private TaskPriority priority;
	private LocalDateTime deadline;
	private UserDTO responsible;
	
	public TaskDTO() {
		
	}

	public TaskDTO(Long id, String title, String description, Boolean status, TaskPriority priority, LocalDateTime deadline, UserDTO responsible) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.status = status;
		this.priority = priority;
		this.deadline = deadline;
		this.responsible = responsible;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public UserDTO getResponsible() {
		return responsible;
	}

	public void setResponsible(UserDTO responsible) {
		this.responsible = responsible;
	}

	public TaskPriority getPriority() {
		return priority;
	}

	public void setPriority(TaskPriority priority) {
		this.priority = priority;
	}

	public LocalDateTime getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDateTime deadline) {
		this.deadline = deadline;
	}
	
	
}
