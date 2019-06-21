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
import megatravel.backend.model.Location;
import megatravel.backend.service.LocationService;

@RestController
@RequestMapping("/location")
public class LocationController {

	@Autowired
	private LocationService locationService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Location> addNewLocation(@RequestBody Location location)
	{
		return new ResponseEntity<Location>(locationService.create(location), HttpStatus.CREATED);
	}
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<LocationListDTO> getAllLocations()
	{
		LocationListDTO locations = new LocationListDTO();
		locations.setLocations(locationService.readAll());
		return new ResponseEntity<LocationListDTO>(locations, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<LocationDTO> getLocationById(@PathVariable("id")Long id)
	{
		LocationDTO location = new LocationDTO();
		location.setLocation(locationService.readById(id));
		return new ResponseEntity<LocationDTO>(location, HttpStatus.OK);
	}
}