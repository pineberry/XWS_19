package megatravel.agentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import megatravel.agentservice.dto.AccommodationUnitDTO;
import megatravel.agentservice.dto.AccommodationUnitListDTO;
import megatravel.agentservice.model.AccommodationUnit;
import megatravel.agentservice.service.AccommodationUnitService;

@RestController
@RequestMapping("/accommodation")
public class AccommodationUnitController {

	@Autowired
	private AccommodationUnitService accommodationUnitService;
	
	//post Accommodation
	@RequestMapping(value = "/postAccommodation", method = RequestMethod.POST)
	public ResponseEntity<AccommodationUnit> postAccommodationUnit(@RequestBody AccommodationUnit accommodationUnit) 
	{
		return new ResponseEntity<AccommodationUnit>(accommodationUnitService.create(accommodationUnit), HttpStatus.CREATED);
	}
	
	
	//get Accommodation by ID
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<AccommodationUnitDTO> getAccommodationUnitById(@PathVariable("id") Long id) 
	{
		AccommodationUnitDTO temp = new AccommodationUnitDTO();
		temp.setAccommodationUnit(accommodationUnitService.readById(id));
		return new ResponseEntity<AccommodationUnitDTO>(temp, HttpStatus.OK);
	}
	
	
	//get all Accommodations
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<AccommodationUnitListDTO> getAllAccommodationUnits() 
	{
		AccommodationUnitListDTO temp = new AccommodationUnitListDTO();
		temp.setAccommodationUnits(accommodationUnitService.readAll());
		return new ResponseEntity<AccommodationUnitListDTO>(temp, HttpStatus.OK);
	}
	
	
	//remove Accommodation
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public ResponseEntity removeAccommodationUnit(@RequestBody AccommodationUnit accommodationUnit) 
	{
		accommodationUnitService.delete(accommodationUnit);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
