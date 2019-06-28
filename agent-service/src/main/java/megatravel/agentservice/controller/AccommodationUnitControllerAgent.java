package megatravel.agentservice.controller;

import java.util.ArrayList;
import java.util.List;

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
import megatravel.agentservice.model.AccommodationUnitAgent;
import megatravel.agentservice.model.AmenityAgent;
import megatravel.agentservice.model.ImageAgent;
import megatravel.agentservice.service.AccommodationUnitServiceAgent;
import megatravel.backend.model.AccommodationUnit;
import megatravel.backend.model.Amenity;
import megatravel.backend.model.Image;
import megatravel.backend.model.Location;


@RestController
@RequestMapping("/accommodation-agent")
public class AccommodationUnitControllerAgent {

	@Autowired
	private AccommodationUnitServiceAgent accommodationUnitService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value = "/postAccommodation", method = RequestMethod.POST)
	public ResponseEntity<AccommodationUnitAgent> postAccommodationUnitAgent(@RequestBody AccommodationUnitAgent accommodationUnit) 
	{
		accommodationUnitService.create(accommodationUnit);
				
		Location location = new Location(accommodationUnit.getLocation().getState(), accommodationUnit.getLocation().getCity(), accommodationUnit.getLocation().getAddress(), accommodationUnit.getLocation().getLatitude(), accommodationUnit.getLocation().getLongitude());
		List<Image> images = new ArrayList<Image>();
		for (ImageAgent image : accommodationUnit.getImages()) {
			images.add(new Image(image.getSrc()));
		}
		List<Amenity> amenities = new ArrayList<Amenity>();
		for (AmenityAgent amenity : accommodationUnit.getAmenities()) {
			amenities.add(new Amenity(amenity.getAmenity()));
		} /*
			 * List<Review> reviews = new ArrayList<Review>(); for (ReviewAgent review :
			 * accommodationUnit.getReviews()) { reviews.add(new
			 * Review(review.getReviewContent(), review.getMark())); }
			 */
		
		AccommodationUnit accommodation = new AccommodationUnit(accommodationUnit.getId(), location, accommodationUnit.getType(), accommodationUnit.getCategory(), accommodationUnit.getDescription(), accommodationUnit.getUnitCapacity(), images, amenities, accommodationUnit.getCancelationPeriod(), accommodationUnit.getPrice(), null, accommodationUnit.getBookedDates());
		
		//sync with main backend db ↓↓↓↓↓↓
		restTemplate.postForObject("http://backend/accommodation/postAccommodation", accommodation, AccommodationUnit.class);
		
		return new ResponseEntity<AccommodationUnitAgent>(accommodationUnit, HttpStatus.CREATED);
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
	public ResponseEntity removeAccommodationUnitAgent(@RequestBody AccommodationUnitAgent accommodationUnit) 
	{
		accommodationUnitService.delete(accommodationUnit);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	//when confirming updateing main db as well
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/{id}/confirm", method = RequestMethod.GET)
	public ResponseEntity confirm(@RequestBody AccommodationUnitAgent accommodationUnit)
	{
		//accommodationUnit.setReserved(true);
		accommodationUnitService.confirmReservation(accommodationUnit);
		//database synchronisation ↓
		restTemplate.postForObject("http://backend/accommodation/postAccommodation", accommodationUnit, AccommodationUnit.class);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
