package com.guilherme.quarkapi.dtos;

import com.guilherme.quarkapi.enums.UserRole;

public class NewUserDTO {
	
	private String userName;
	
	private String firstName;
	
	private String lastName;
	
	private String password;
	
	private UserRole role;
	
	public NewUserDTO() {
		
	}
	
	public NewUserDTO(String userName, String firstName, String lastName, String password, UserRole role) {
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPassword() {
		return password;
	}
	
	public UserRole getRole() {
		return role;
	}

	public String getUserName() {
		return userName;
	}
}
