package com.guilherme.quarkapi.dtos;

import com.guilherme.quarkapi.enums.TaskPriority;

public class TaskFilterDTO {

	private Long id;
    private String titleOrDescription;
    private Long userId;
    private TaskPriority priority;
    private Boolean status;

    
    
	public TaskFilterDTO(Long id, String titleOrDescription, Long userId, TaskPriority priority, Boolean status) {
		this.id = id;
		this.titleOrDescription = titleOrDescription;
		this.userId = userId;
		this.priority = priority;
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public String getTitleOrDescription() {
		return titleOrDescription;
	}
	public Long getUserId() {
		return userId;
	}
	public TaskPriority getPriority() {
		return priority;
	}
	public Boolean getStatus() {
		return status;
	}
}
