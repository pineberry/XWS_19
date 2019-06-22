package megatravel.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import megatravel.backend.model.Message;
import megatravel.backend.repository.MessageRepository;

@Service
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;
	
	//create
	public Message create(Message message)
	{
		return messageRepository.save(message);
	}
	
	//read all
	public List<Message> readAll()
	{
		return messageRepository.findAll();
	}
	
	//read by id
	public Optional<Message> readById(Long id)
	{
		return messageRepository.findById(id);
	}
}
