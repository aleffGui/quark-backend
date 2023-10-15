package com.guilherme.quarkapi.dtos;

public class LoginResponseTokenDTO {

	private String token;
	
	public LoginResponseTokenDTO() {
		
	}
	
	public LoginResponseTokenDTO(String token) {
		this.token = token;
	}
	
	public String getToken() {
		return token;
	}
}
