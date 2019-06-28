package megatravel.agentservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import megatravel.agentservice.repository.LocationRepositoryAgent;
import megatravel.agentservice.model.LocationAgent;

@Service
public class LocationServiceAgent {

	@Autowired
	private LocationRepositoryAgent locationRepository;
	
	public LocationAgent create(LocationAgent location)
	{
		return locationRepository.save(location);
	}
	
	public List<LocationAgent> readAll() 
	{
		return locationRepository.findAll();
	}
	
	public Optional<LocationAgent> readById(Long id)
	{
		return locationRepository.findById(id);
	}	
}