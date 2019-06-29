package megatravel.agentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import megatravel.agentservice.model.AmenityAgent;
import megatravel.agentservice.service.AmenityServiceAgent;

@RestController
@RequestMapping("/amenity-agent")
public class AmenityControllerAgent {

	@Autowired
	private AmenityServiceAgent amenityServiceAgent;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<String> addNewAmenity(@RequestParam("amenity")  String amenity)
	{
		System.out.println(amenity);
		amenityServiceAgent.create(new AmenityAgent(amenity));
		
		//sync with main db ↓↓↓↓↓↓
		restTemplate.postForObject("http://backend/amenity/add?amenity=" + amenity, amenity, String.class);
		
		return new ResponseEntity<String>(amenity, HttpStatus.CREATED);
	}
	
}
