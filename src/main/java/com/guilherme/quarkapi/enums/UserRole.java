package com.guilherme.quarkapi.enums;

public enum UserRole {
	ADMIN("Usuario"),
	USER("Administrador");
	
	private String role;
	
	UserRole(String role) {
		this.role = role;
	}
	
	public String getRole() {
		return role;
	}
	
	
}
