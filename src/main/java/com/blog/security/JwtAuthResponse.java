package com.blog.security;

public class JwtAuthResponse {
	
	
	

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	private String token;

	public JwtAuthResponse(String token) {
		super();
		this.token = token;
	}

	public JwtAuthResponse() {
		// TODO Auto-generated constructor stub
	}
	
	

}
