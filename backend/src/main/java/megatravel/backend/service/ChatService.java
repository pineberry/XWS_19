package megatravel.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import megatravel.backend.dto.ChatDTO;
import megatravel.backend.model.Chat;
import megatravel.backend.repository.ChatRepository;

@Service
public class ChatService {

	@Autowired
	private ChatRepository chatRepository;

	//create
	public Chat create(Chat chat)
	{
		return chatRepository.save(chat);
	}
	
	//read
	public ChatDTO read(Long id) 
	{ 
		ChatDTO chat = new ChatDTO();
		chat.setChat(chatRepository.findById(id));
		return chat;
	}
	
	//update
	public void update(Chat chat, Long id)
	{
		Optional<Chat> temp = chatRepository.findById(id);
		if (temp.isPresent()) {
			Chat chat_ = chat;
			chat_.setId(temp.get().getId());
			chatRepository.save(chat_);
		}
	}
}
