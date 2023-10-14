package com.guilherme.quarkapi.models;

import java.time.LocalDateTime;
import java.util.Objects;

import com.guilherme.quarkapi.enums.TaskPriority;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "task")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	private String description;

	@Enumerated(EnumType.STRING)
	private TaskPriority priority;

	private LocalDateTime deadline;

	private Boolean status;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Task(Long id, String title, String description, User user, TaskPriority priority, LocalDateTime deadline, Boolean status) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.user = user;
		this.priority = priority;
		this.deadline = deadline;
		this.status = status;
	}

	public Task(String title, String description, TaskPriority priority, LocalDateTime deadline, Boolean status) {
		super();
		this.title = title;
		this.description = description;
		this.priority = priority;
		this.deadline = deadline;
		this.status = status;
	}

	public Task() {

	}
	
	public Long getId() {
		return id;
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

	public User getUser() {
		return user;
	}

	public void setResponsible(User user) {
		this.user = user;
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		return Objects.equals(id, other.id);
	}
}
