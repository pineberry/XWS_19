package megatravel.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import megatravel.backend.model.User;
import megatravel.backend.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	//create
	public User create(User user) 
	{
		if(!userAlreadyRegistered(user))
		{
			return userRepository.save(user);
		}
		System.out.println("*******Exception, look up in UserService create(User user) method!*******");
		return null;
	}
	
	//read by ID
	public Optional<User> readById(Long id)
	{
		return userRepository.findById(id);
	}
	
	//read all
	public List<User> readAll() 
	{
		return userRepository.findAll();
	}
	
	//update
	//delete
	
	//help method that checks if an user already registered, returns false if it is not registered
	private boolean userAlreadyRegistered(User user) 
	{
		for (User temp : userRepository.findAll()) 
		{
			if(temp.getUsername().equals(user.getUsername()))
				return true;
		}
		return false;
	}
	
}
