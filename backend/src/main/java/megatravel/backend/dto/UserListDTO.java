package megatravel.backend.dto;

import java.util.List;

import lombok.Data;
import megatravel.backend.model.User;

@Data
public class UserListDTO {

	private List<User> users;
	
}
