package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.exception.ApiException;
import com.blog.security.JwtAuthRequest;
import com.blog.security.JwtAuthResponse;
import com.blog.security.JwtTokenHelper;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {

		
		this.authenticate(request.getUsername(), request.getPassword());
			
		 

		UserDetails userDetail = this.userDetailsService.loadUserByUsername(request.getUsername());

		String token = this.jwtTokenHelper.generateToken(userDetail);
		
		
		JwtAuthResponse jwtAuthResponse=new JwtAuthResponse();
		
		jwtAuthResponse.setToken(token);
		
		

		return new  ResponseEntity<JwtAuthResponse>(jwtAuthResponse,HttpStatus.OK);

	}

	
	
	
	
	private void authenticate(String username, String password) throws Exception {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);

		try {

			this.authenticationManager.authenticate(authenticationToken);

		} catch (DisabledException e) {

			throw new Exception("USER DISABLED" + e.getMessage());

		} catch (BadCredentialsException e) {

			//throw new Exception("Invalid credential" + e.getMessage());
			throw new ApiException("Invalid username or password" );
		}

	}
	

}
