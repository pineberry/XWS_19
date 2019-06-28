package megatravel.agentservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import megatravel.agentservice.model.ImageAgent;
import megatravel.agentservice.repository.ImageRepositoryAgent;

@Service
public class ImageServiceAgent {

	@Autowired
	private ImageRepositoryAgent imageRepositoryAgent;
	
	public ImageAgent create(ImageAgent image)
	{
		return imageRepositoryAgent.save(image);
	}
	
	public List<ImageAgent> readAll() 
	{
		return imageRepositoryAgent.findAll();
	}
	
	public Optional<ImageAgent> readById(Long id)
	{
		return imageRepositoryAgent.findById(id);
	}
	
}
