package com.harroshan.user.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harroshan.user.models.CreateUserRequest;
import com.harroshan.user.models.CreateUserResponse;
import com.harroshan.user.models.DeleteUser;
import com.harroshan.user.models.UserDto;
import com.harroshan.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UsersController {

	@Autowired
	UserService userService;

	@GetMapping("/status/check")
	public String status() {
		return "Get Mapping checked";
	}

	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<CreateUserResponse> createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		UserDto userDto = modelMapper.map(createUserRequest, UserDto.class);

		UserDto createdUser = userService.createUser(userDto);

		CreateUserResponse createUserResponse = modelMapper.map(createdUser, CreateUserResponse.class);

		return new ResponseEntity<>(createUserResponse, HttpStatus.ACCEPTED);
	}

	@DeleteMapping
	public UserDto deleteUser(@RequestBody DeleteUser deleteUser) {

		return userService.getUserDetailsByEmail(deleteUser.getEmail());
	}

}
