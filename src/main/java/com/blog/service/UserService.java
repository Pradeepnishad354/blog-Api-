package com.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.payloads.UserDto;

@Service
public interface UserService {
	
	UserDto createUser(UserDto userDto);
	
	UserDto updateUser(UserDto userDto,int userId);
	
	UserDto getUserById(Integer userId);
	
	List<UserDto>  getAllUser();
	
	void deleteUser(Integer userId);

}
