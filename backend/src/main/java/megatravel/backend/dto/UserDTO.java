package megatravel.backend.dto;

import java.util.Optional;

import lombok.Data;
import megatravel.backend.model.User;

@Data
public class UserDTO {

	private Optional<User> user;
	
}
