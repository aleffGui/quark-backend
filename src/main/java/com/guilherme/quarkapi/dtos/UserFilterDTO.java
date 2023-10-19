package com.guilherme.quarkapi.dtos;

import com.guilherme.quarkapi.enums.UserRole;

public class UserFilterDTO {

	private Long id;
	private String firstNameOrLastName;
	private String userName;
	private UserRole role;
	
	public UserFilterDTO(Long id, String firstNameOrLastName, String userName, UserRole role) {
		super();
		this.id = id;
		this.firstNameOrLastName = firstNameOrLastName;
		this.userName = userName;
		this.role = role;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getFirstNameOrLastName() {
		return firstNameOrLastName;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public UserRole getRole() {
		return role;
	}
}
