package com.blog.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.constant.ApiConstant;
import com.blog.entity.Role;
import com.blog.entity.User;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payloads.UserDto;
import com.blog.repository.RoleRepository;
import com.blog.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	
	
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	

	@Override
	public UserDto createUser(UserDto userDto) {

		User user = this.dtoToUser(userDto);
		
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		
		// role
		
		
		//Role role = this.roleRepository.findById(ApiConstant.NORMAl_USER).get();
		
	
		
		// user.getRoles().add(role);
		
		
		
		
		User savedUser = userRepository.save(user);
		
		

		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, int userId) {

		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User updatedUser = this.userRepository.save(user);

		UserDto userToDto = this.userToDto(updatedUser);

		return userToDto;
	}

	@Override
	public UserDto getUserById(Integer userId) {

		User findById = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		return this.userToDto(findById);
	}

	@Override
	public List<UserDto> getAllUser() {

		List<User> users = this.userRepository.findAll();

		List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());

		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		
		User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        this.userRepository.delete(user);
	}

	public User dtoToUser(UserDto userDto) {

		
		User user=this.modelMapper.map(userDto,User.class);
//		User user = new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());

		return user;

	}

	public UserDto userToDto(User user) {
		
		UserDto userDto=this.modelMapper.map(user,UserDto
				.class);

//		UserDto userDto = new UserDto();
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());

		return userDto;

	}

}
