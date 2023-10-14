package com.guilherme.quarkapi.dtos;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.guilherme.quarkapi.constants.ValidatorConstant;
import com.guilherme.quarkapi.enums.TaskPriority;


public class NewTaskDTO {
	
	@NotEmpty(message = ValidatorConstant.REQUIRED_MESSAGE)
	@Length(max = 100, message = ValidatorConstant.MAX_LENGTH_MESSAGE + "100 caracteres.")
	private String title;
	
	@NotEmpty(message = ValidatorConstant.REQUIRED_MESSAGE)
	@Length(max = 255, message = ValidatorConstant.MAX_LENGTH_MESSAGE + "255 caracteres." )
	private String description;
	
	@NotNull(message = ValidatorConstant.REQUIRED_MESSAGE)
	private TaskPriority priority;
	
	@NotNull(message = ValidatorConstant.REQUIRED_MESSAGE)
	private LocalDateTime deadline;
	
	@NotNull(message = ValidatorConstant.REQUIRED_MESSAGE)
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
