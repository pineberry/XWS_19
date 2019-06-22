package megatravel.backend.dto;

import java.util.Optional;

import lombok.Data;
import megatravel.backend.model.Message;

@Data
public class MessageDTO {

	private Optional<Message> message;
	
}
