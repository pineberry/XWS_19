package megatravel.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import megatravel.backend.model.Amenity;
import megatravel.backend.repository.AmenityRepository;

@Service
public class AmenityService {

	@Autowired
	private AmenityRepository amenityRepository;
	
	public List<Amenity> readAll() 
	{
		return amenityRepository.findAll();
	}
	
	public Amenity create(String amenityName)
	{
		Amenity amenity = new Amenity();
		amenity.setAmenity(amenityName);
		return amenityRepository.save(amenity);
	}
	
}
