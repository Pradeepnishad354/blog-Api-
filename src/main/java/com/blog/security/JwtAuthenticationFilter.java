package com.blog.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;



import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		
		//get token
		
		
		
		
		 String requestToken = request.getHeader("Authorization");
			
			//System.out.println(requestTokenHeader+"jfihwqfhpwqhf");

			String username = null;
			String token = null;

			if (requestToken != null && requestToken.startsWith("Bearer ")) {

				
					// yes token is valid

				token = requestToken.substring(7);

					try {
						username =this.jwtTokenHelper.extractUsername(token);
						System.out.println(username+"fhewhfyp9");
					} catch (ExpiredJwtException e) {

						e.printStackTrace();

						System.out.println("jwt token has expired ");
					}catch(Exception e) {
						
						e.printStackTrace();
						System.out.println("error");
					}
				} else {

					System.out.println("Invalid Tooken ,not start with Bearer string");
				}
			
			
			
				// validate token

				if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

					final UserDetails userDetails = this.customUserDetailService.loadUserByUsername(username);

					if (this.jwtTokenHelper.validateToken(token, userDetails)) {
						
						// token is valid

						UsernamePasswordAuthenticationToken usernamePasswordAuthentication = new UsernamePasswordAuthenticationToken(
								userDetails, null, userDetails.getAuthorities());

						usernamePasswordAuthentication
								.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

						SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthentication);

					} 
				}else {

						System.out.println("Token is not valid");
					}
				
			
				filterChain.doFilter(request, response);

		
		
	}

}
