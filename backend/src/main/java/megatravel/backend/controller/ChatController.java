package megatravel.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import megatravel.backend.dto.ChatDTO;
import megatravel.backend.model.Chat;
import megatravel.backend.model.Message;
import megatravel.backend.service.ChatService;
import megatravel.backend.service.MessageService;

@RestController
@RequestMapping("/chat")
public class ChatController {

	@Autowired
	private ChatService chatService;
	
	@Autowired
	private MessageService messageService;
	
	@RequestMapping(value = "/start-chat", method = RequestMethod.POST)
	public ResponseEntity<Chat>	createChat(@RequestBody Chat chat) {
		//get sent message and save it, essentially it's the first message in the chat
		messageService.create(chat.getMessages().get(0));
		return new ResponseEntity<Chat>(chatService.create(chat), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ChatDTO> getAllMessagesInChat(@PathVariable("id") Long id)
	{
		ChatDTO chat = new ChatDTO();
		chat = chatService.read(id);
		return new ResponseEntity<ChatDTO>(chat, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}/update", method = RequestMethod.PUT)
	public ResponseEntity<ChatDTO> updateChat(@RequestBody Message message, @PathVariable Long id)
	{
		// get chat (with provided ID) that should be updated with new message
		ChatDTO chat = chatService.read(id);
		// get the list of messages that the chat already contains and add this new one
		List<Message> messages = chat.getChat().get().getMessages();
		messages.add(message);
		// set new list of messages to the chat
		chat.getChat().get().setMessages(messages);
		// save changes
		chatService.update(chat.getChat().get(), id);
		//mozda ce da treba chat id i ko je sender i receiver
		messageService.create(message);
		return new ResponseEntity<ChatDTO>(chat, HttpStatus.OK);
	}
}
