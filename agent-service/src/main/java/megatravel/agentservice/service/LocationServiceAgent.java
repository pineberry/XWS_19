package megatravel.agentservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import megatravel.agentservice.model.Location;
import megatravel.agentservice.repository.LocationRepository;

@Service
public class LocationServiceAgent {

	@Autowired
	private LocationRepository locationRepository;
	
	public Location create(Location location)
	{
		return locationRepository.save(location);
	}
	
	public List<Location> readAll() 
	{
		return locationRepository.findAll();
	}
	
	public Optional<Location> readById(Long id)
	{
		return locationRepository.findById(id);
	}	
}