

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CreateUserRequest {

	@NotNull(message = "firstName cannot be null")
	@Size(min = 2, message = "firstName must not be less than 2 chars")
	private String firstName;

	@NotNull(message = "lastName cannot be null")
	@Size(min = 2, message = "lastName must not be less than 2 chars")
	private String lastName;

	@NotNull(message = "password cannot be null")
	@Size(min = 8, max = 16, message = "password must not be less than 2 chars and must not exceed 16 chars")
	private String password;

	@NotNull(message = "email cannot be null")
	@Email
	private String email;
}