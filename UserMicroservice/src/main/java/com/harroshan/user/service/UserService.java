package com.harroshan.user.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.harroshan.user.models.UserDto;

public interface UserService extends UserDetailsService {

	UserDto createUser(UserDto userDto);
	
	UserDto getUserDetailsByEmail(String email);
}
