package megatravel.backend.dto;


import java.util.Optional;

import lombok.Data;
import megatravel.backend.model.Chat;

@Data
public class ChatDTO {

	private Optional<Chat> chat;
	
}
