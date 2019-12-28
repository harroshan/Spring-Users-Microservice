package com.harroshan.user.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.harroshan.user.models.LoginRequest;
import com.harroshan.user.models.UserDto;
import com.harroshan.user.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private UserService userService;

	private Environment environment;

	public AuthenticationFilter(AuthenticationManager authenticationManager, UserService userService, Environment env) {
		this.userService = userService;
		this.environment = env;
		super.setAuthenticationManager(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {
			System.out.println("Filter working");
			LoginRequest creds = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);

			return getAuthenticationManager().authenticate(
					new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		String username = ((User) authResult.getPrincipal()).getUsername();

		System.out.println(username + "1");
		System.out.println(userService);
		UserDto userDto = userService.getUserDetailsByEmail(username);

		String token = Jwts.builder().setSubject(userDto.getUserId())
				.signWith(SignatureAlgorithm.HS512, environment.getProperty("token.secret"))
				.setExpiration(new Date(
						System.currentTimeMillis() + Long.parseLong(environment.getProperty("token.expiration.time"))))
				.compact();

		response.addHeader("Token", token);
		response.addHeader("userId", userDto.getUserId());
	}

}
