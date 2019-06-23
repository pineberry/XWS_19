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

import megatravel.agentservice.dto.AccommodationUnitDTO;
import megatravel.agentservice.dto.AccommodationUnitListDTO;
import megatravel.backend.model.AccommodationUnit;
import megatravel.agentservice.service.AccommodationUnitServiceAgent;


@RestController
@RequestMapping("/accommodation-agent")
public class AccommodationUnitControllerAgent {

	@Autowired
	private AccommodationUnitServiceAgent accommodationUnitService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	//post Accommodation
	@RequestMapping(value = "/postAccommodation", method = RequestMethod.POST)
	public ResponseEntity<AccommodationUnit> postAccommodationUnitAgent(@RequestBody AccommodationUnit accommodationUnit) 
	{
		accommodationUnitService.create(accommodationUnit);
		restTemplate.postForObject("http://backend/accommodation/postAccommodation", accommodationUnit, AccommodationUnit.class);
		return new ResponseEntity<AccommodationUnit>(accommodationUnit, HttpStatus.CREATED);
	}

	//get Accommodation by ID
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<AccommodationUnitDTO> getAccommodationUnitByIdAgent(@PathVariable("id") Long id) 
	{
		AccommodationUnitDTO temp = new AccommodationUnitDTO();
		temp.setAccommodationUnit(accommodationUnitService.readById(id));
		return new ResponseEntity<AccommodationUnitDTO>(temp, HttpStatus.OK);
	}
	
	
	//get all Accommodations
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<AccommodationUnitListDTO> getAllAccommodationUnitsAgent() 
	{
		AccommodationUnitListDTO temp = new AccommodationUnitListDTO();
		temp.setAccommodationUnits(accommodationUnitService.readAll());
		return new ResponseEntity<AccommodationUnitListDTO>(temp, HttpStatus.OK);
	}
	
	
	//remove Accommodation
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public ResponseEntity removeAccommodationUnitAgent(@RequestBody AccommodationUnit accommodationUnit) 
	{
		accommodationUnitService.delete(accommodationUnit);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/{id}/confirm", method = RequestMethod.GET)
	public ResponseEntity confirm(@RequestBody AccommodationUnit accommodationUnit)
	{
		//accommodationUnitService.create(accommodationUnit);
		accommodationUnit.setReserved(true);
		accommodationUnitService.confirmReservation(accommodationUnit);
		restTemplate.postForObject("http://backend/accommodation/postAccommodation", accommodationUnit, AccommodationUnit.class);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
