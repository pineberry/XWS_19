package megatravel.agentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import megatravel.agentservice.dto.LocationDTO;
import megatravel.agentservice.dto.LocationListDTO;
import megatravel.agentservice.model.Location;
import megatravel.agentservice.service.LocationServiceAgent;

@RestController
@RequestMapping("/location-agent")
public class LocationControllerAgent {

	@Autowired
	private LocationServiceAgent locationService;

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
