package megatravel.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import megatravel.backend.SOAPConnector;
import megatravel.backend.dto.AccommodationUnitDTO;
import megatravel.backend.dto.AccommodationUnitListDTO;
import megatravel.backend.model.AccommodationUnit;
import megatravel.backend.repository.AccommodationUnitRepository;
import megatravel.backend.service.AccommodationUnitService;
import megatravel.backend.service.LocationService;
import megatravel.backend.soap.AddAccommodationUnitRequest;
import megatravel.backend.soap.AddAccommodationUnitResponse;
import megatravel.backend.soap.AddLocationRequest;
import megatravel.backend.soap.AddLocationResponse;


@RestController
@RequestMapping("/accommodations")
public class AccommodationUnitController {
	
	@Autowired
	SOAPConnector soapConnector;

	@Autowired
	private AccommodationUnitService accommodationUnitService;
	
	@Autowired
	private AccommodationUnitRepository accommodationUnitRepository;
	
	@Autowired
	private LocationService locationService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<AccommodationUnitListDTO> getAllAccommodationUnits() 
	{
		AccommodationUnitListDTO temp = new AccommodationUnitListDTO();
		temp.setAccommodationUnits(accommodationUnitRepository.findAll());
		
		return new ResponseEntity<AccommodationUnitListDTO>(temp, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<AccommodationUnit> postAccommodationUnit(@RequestBody AccommodationUnit accommodationUnit) 
	{
		AddAccommodationUnitRequest reqAccommodation = new AddAccommodationUnitRequest();
		AddLocationRequest reqLocation = new AddLocationRequest();
		reqAccommodation.setAccommodationUnit(accommodationUnit);
		reqLocation.setLocation(accommodationUnit.getLocation());
		
		locationService.create(accommodationUnit.getLocation());
		accommodationUnitService.create(accommodationUnit);
		
		AddLocationResponse resLocation = (AddLocationResponse) soapConnector.callWebService("http://localhost:8081/ws/schemas", reqLocation);
		System.out.println(resLocation.getLocation());
		AddAccommodationUnitResponse resAccommodation = (AddAccommodationUnitResponse) soapConnector.callWebService("http://localhost:8081/ws/schemas", reqAccommodation);
		System.out.println(resAccommodation.getAccommodationUnit());
		
		return new ResponseEntity<AccommodationUnit>(resAccommodation.getAccommodationUnit(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<AccommodationUnitListDTO> getAllAccommodationUnitsAgent(@RequestParam("id") String id) {
		AccommodationUnitListDTO temp = new AccommodationUnitListDTO();
		temp.setAccommodationUnits(accommodationUnitService.readAll(Integer.parseInt(id)));
		return new ResponseEntity<AccommodationUnitListDTO>(temp, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<AccommodationUnitDTO> getAccommodationUnitById(@PathVariable("id") Long id) 
	{
		AccommodationUnitDTO temp = new AccommodationUnitDTO();
		temp.setAccommodationUnit(accommodationUnitService.readById(id));
		
		return new ResponseEntity<AccommodationUnitDTO>(temp, HttpStatus.OK);
	}
	

	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public ResponseEntity<Object> removeAccommodationUnit(@RequestBody AccommodationUnit accommodationUnit) 
	{
		accommodationUnitService.delete(accommodationUnit);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
