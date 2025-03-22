package com.guilherme.quarkapi.enums;

public enum UserRole {
	USER("Usuario"),
	ADMIN("Administrador");
	
	private String role;
	
	UserRole(String role) {
		this.role = role;
	}
	
	public String getRole() {
		return role;
	}
	
	
}
