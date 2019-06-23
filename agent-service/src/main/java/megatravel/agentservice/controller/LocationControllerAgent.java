package megatravel.agentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import megatravel.agentservice.dto.LocationDTO;
import megatravel.agentservice.dto.LocationListDTO;
import megatravel.agentservice.service.LocationServiceAgent;
import megatravel.agentservice.model.Location;

@RestController
@RequestMapping("/location-agent")
public class LocationControllerAgent {

	@Autowired
	private LocationServiceAgent locationService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Location> addNewLocation(@RequestBody Location location)
	{
		locationService.create(location);
		restTemplate.postForObject("http://backend/location/add", location, Location.class);
		return new ResponseEntity<Location>(location, HttpStatus.CREATED);
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
