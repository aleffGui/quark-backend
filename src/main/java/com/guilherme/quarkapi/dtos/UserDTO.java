package com.guilherme.quarkapi.dtos;

import com.guilherme.quarkapi.enums.UserRole;

public class UserDTO {

	private Long id;
	private String firstName;
	private String lastName;
	private String userName;
	private UserRole role;
	
	public UserDTO() {
		
	}

	public UserDTO(Long id, String firstName, String lastName, String userName, UserRole role) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.userName = userName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public UserRole getRole() {
		return role;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}
	
	
}
