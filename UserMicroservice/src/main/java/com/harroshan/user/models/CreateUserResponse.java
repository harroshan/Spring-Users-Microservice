package com.harroshan.user.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateUserResponse {

	private String firstName;
	private String lastName;
	private String email;
	private String userId;

}
