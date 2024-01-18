package com.blog.payloads;
//
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.Size;


public class UserDto {

	private int id;
	
//	@NotEmpty
//	@Size(min = 4,message = "username must be  minimum  4character ")
	private String name;

	//@Email(message = "emailis not valid")
	
	private String email;
	
	//@NotEmpty
	//@Size(min = 4 ,max = 10 ,message = "minimum 4 char and maximum 10 char")
	private String password;
	
	//@NotEmpty
	
	private String about;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public UserDto(int id, String name, String email, String password, String about) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
	}

	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	
	

}
