package megatravel.agentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;

import megatravel.agentservice.dto.AccommodationUnitDTO;
import megatravel.agentservice.dto.AccommodationUnitListDTO;
import megatravel.agentservice.model.AccommodationUnit;
import megatravel.agentservice.service.AccommodationUnitServiceAgent;
import megatravel.agentservice.service.LocationServiceAgent;

@RestController
@RequestMapping("/agent/accommodations")
public class AccommodationUnitControllerAgent {

	@Autowired
	private AccommodationUnitServiceAgent accommodationUnitService;

	@Autowired
	private LocationServiceAgent locationService;

	//@Autowired
	//private RestTemplate restTemplate;
	
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public ResponseEntity<AccommodationUnit> postAccommodationUnit(@RequestBody AccommodationUnit accUnit){
		locationService.create(accUnit.getLocation());
		System.out.println(accUnit);
		accommodationUnitService.create(accUnit);
		//Location location = new Location(accUnit.getLocation().getId(), accUnit.getLocation().getState(), accUnit.getLocation().getCity(), accUnit.getLocation().getAddress());
		/*AccommodationUnit accommodation = new AccommodationUnit(accUnit.getHostId(), location, accUnit.getType(),
				accUnit.getCategory(), accUnit.getDescription(), accUnit.getUnitCapacity(), null,
				accUnit.getAmenities(), accUnit.getCancelationPeriod(), accUnit.getDefaultPrice(), accUnit.getPricePlan(), 
				accUnit.getBookedDates());*/
		//sync with main backend db ↓↓↓↓↓↓
		//restTemplate.postForObject("http://backend/locations/add", location, Location.class);
		//restTemplate.postForObject("http://backend/accommodations/add", accommodation, AccommodationUnit.class);
		return new ResponseEntity<AccommodationUnit>(accUnit, HttpStatus.OK);
	}

	// get Accommodation by ID
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<AccommodationUnitDTO> getAccommodationUnitByIdAgent(@PathVariable("id") Long id) {
		AccommodationUnitDTO temp = new AccommodationUnitDTO();
		temp.setAccommodationUnit(accommodationUnitService.readById(id));
		return new ResponseEntity<AccommodationUnitDTO>(temp, HttpStatus.OK);
	}

	// get all Accommodations
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<AccommodationUnitListDTO> getAllAccommodationUnitsAgent(@RequestParam("id") String id) {
		AccommodationUnitListDTO temp = new AccommodationUnitListDTO();
		temp.setAccommodationUnits(accommodationUnitService.readAll(Integer.parseInt(id)));
		return new ResponseEntity<AccommodationUnitListDTO>(temp, HttpStatus.OK);
	}

	// remove Accommodation
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ResponseEntity removeAccommodationUnit(@RequestBody AccommodationUnit accommodationUnit) {
		accommodationUnitService.delete(accommodationUnit);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// when confirming updateing main db as well
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/{id}/confirm", method = RequestMethod.GET)
	public ResponseEntity confirm(@RequestBody AccommodationUnit accommodationUnit) {
		// accommodationUnit.setReserved(true);
		accommodationUnitService.confirmReservation(accommodationUnit);
		// database synchronisation ↓
		//restTemplate.postForObject("http://backend/accommodations/add", accommodationUnit, AccommodationUnit.class);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
