package megatravel.agentservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import megatravel.agentservice.model.UserAgent;
import megatravel.agentservice.repository.UserRepositoryAgent;

@Service
public class UserServiceAgent {

	@Autowired
	private UserRepositoryAgent userRepositoryAgent;
	
	public UserAgent create(UserAgent user)
	{
		return userRepositoryAgent.save(user);
	}
	
}
