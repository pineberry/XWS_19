package megatravel.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import megatravel.backend.dto.LocationDTO;
import megatravel.backend.dto.LocationListDTO;
import megatravel.backend.service.LocationService;
import megatravel.backend.model.Location;

@RestController
@RequestMapping("/locations")
public class LocationController {

	@Autowired
	private LocationService locationService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<LocationListDTO> getAllLocations()
	{
		LocationListDTO locations = new LocationListDTO();
		//locations.setLocations(locationService.readAll());
		
		return new ResponseEntity<LocationListDTO>(locations, HttpStatus.OK);
	}
			
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Location> addNewLocation(@RequestBody Location location)
	{
		return new ResponseEntity<Location>(HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<LocationDTO> getLocationById(@PathVariable("id")Long id)
	{
		LocationDTO location = new LocationDTO();
		//location.setLocation(locationService.readById(id));
		
		return new ResponseEntity<LocationDTO>(location, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
	public ResponseEntity<LocationDTO> update(@RequestBody Location location, @PathVariable("id")Long id)
	{
		LocationDTO temp = new LocationDTO();
		//locationService.create(location);
		//temp.setLocation(locationService.readById(id));
		return new ResponseEntity<LocationDTO>(temp, HttpStatus.OK);
	}
}
