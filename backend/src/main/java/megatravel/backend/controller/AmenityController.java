package megatravel.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import megatravel.backend.dto.AmenityListDTO;
import megatravel.backend.service.AmenityService;

@RestController
@RequestMapping("/amenity")
public class AmenityController {

	@Autowired
	private AmenityService amenityService;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<AmenityListDTO> getAllAmenities() 
	{
		AmenityListDTO amenities = new AmenityListDTO();
		amenities.setAmenities(amenityService.readAll());
		return new ResponseEntity<AmenityListDTO>(amenities, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<String> addNewAmenity(@RequestBody String amenity)
	{
		amenityService.create(amenity);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
}
