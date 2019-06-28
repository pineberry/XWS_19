package megatravel.agentservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import megatravel.agentservice.model.AmenityAgent;
import megatravel.agentservice.repository.AmenityRepositoryAgent;

@Service
public class AmenityServiceAgent {

	@Autowired
	private AmenityRepositoryAgent amenityRepositoryAgent;
	
	public AmenityAgent create(AmenityAgent amenity)
	{
		return amenityRepositoryAgent.save(amenity);
	}
	
	public List<AmenityAgent> readAll() 
	{
		return amenityRepositoryAgent.findAll();
	}
	
	public Optional<AmenityAgent> readById(Long id)
	{
		return amenityRepositoryAgent.findById(id);
	}
}
