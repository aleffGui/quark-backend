package com.guilherme.quarkapi.dtos;

import java.time.LocalDateTime;

import com.guilherme.quarkapi.enums.TaskPriority;

public class NewTaskDTO {
	
	private String title;
	
	private String description;
	
	private TaskPriority priority;
	
	private LocalDateTime deadline;
	
	private Long userId;
	
	public NewTaskDTO() {
		
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
}
