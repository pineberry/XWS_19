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
import megatravel.agentservice.model.AccommodationUnitAgent;
import megatravel.agentservice.service.AccommodationUnitServiceAgent;
import megatravel.agentservice.service.LocationServiceAgent;
import megatravel.backend.model.AccommodationUnit;

@RestController
@RequestMapping("/agent/accommodations")
public class AccommodationUnitControllerAgent {

	@Autowired
	private AccommodationUnitServiceAgent accommodationUnitService;

	@Autowired
	private LocationServiceAgent locationService;

	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public ResponseEntity<AccommodationUnitAgent> postAccommodationUnit(@RequestBody AccommodationUnitAgent accUnit){
		locationService.create(accUnit.getLocation());
		System.out.println(accUnit);
		accommodationUnitService.create(accUnit);
		
		return new ResponseEntity<AccommodationUnitAgent>(accUnit, HttpStatus.OK);
	}

	/*
	 * @RequestMapping(value = "/postAccommodation", method = RequestMethod.POST)
	 * public ResponseEntity<AccommodationUnitAgent>
	 * postAccommodationUnitAgent(@RequestParam(value = "hostId") String
	 * hostID, @RequestParam("locationID") String locationID,
	 * 
	 * @RequestParam("type") String type, @RequestParam("category") String
	 * category, @RequestParam("description") String
	 * description, @RequestParam("unitCapacity") String unitCapacity,
	 * 
	 * @RequestParam("cancelationPeriod") String
	 * cancelationPeriod, @RequestParam("price") String price) { Long id =
	 * Long.parseLong(locationID); Optional<LocationAgent> location =
	 * locationService.readById(id); AccommodationUnitAgent accommodationUnit = new
	 * AccommodationUnitAgent(Long.parseLong(hostID.split("&")[0]), new
	 * LocationAgent(id, location.get().getState(), location.get().getCity(),
	 * location.get().getAddress(), location.get().getLatitude(),
	 * location.get().getLongitude()), type, category, description,
	 * Integer.parseInt(unitCapacity), null, null,
	 * Long.parseLong(cancelationPeriod), Double.parseDouble(price), null);
	 * 
	 * System.out.println(">>>>>>>>>>>"+ accommodationUnit);
	 * accommodationUnitService.create(accommodationUnit);
	 * 
	 * Location location_ = new Location(location.get().getId(),
	 * accommodationUnit.getLocation().getState(),
	 * accommodationUnit.getLocation().getCity(),
	 * accommodationUnit.getLocation().getAddress(),
	 * accommodationUnit.getLocation().getLatitude(),
	 * accommodationUnit.getLocation().getLongitude());
	 * System.out.println(location);
	 * 
	 * List<Image> images = new ArrayList<Image>(); for (ImageAgent image :
	 * accommodationUnit.getImages()) { images.add(new Image(image.getSrc())); }
	 * List<Amenity> amenities = new ArrayList<Amenity>(); for (AmenityAgent amenity
	 * : accommodationUnit.getAmenities()) { amenities.add(new
	 * Amenity(amenity.getAmenity())); }
	 * 
	 * List<Review> reviews = new ArrayList<Review>(); for (ReviewAgent review :
	 * accommodationUnit.getReviews()) { reviews.add(new
	 * Review(review.getReviewContent(), review.getMark())); }
	 * 
	 * 
	 * AccommodationUnit accommodation = new
	 * AccommodationUnit(Long.parseLong(hostID.split("&")[0]), location_,
	 * accommodationUnit.getType(), accommodationUnit.getCategory(),
	 * accommodationUnit.getDescription(), accommodationUnit.getUnitCapacity(),
	 * null, null, accommodationUnit.getCancelationPeriod(),
	 * accommodationUnit.getPrice(), null, accommodationUnit.getBookedDates());
	 * System.out.println(">>>>>>>>>>>"+ Long.parseLong(hostID.split("&")[0]));
	 * System.out.println(accommodation); //sync with main db ↓↓↓↓↓↓
	 * restTemplate.postForObject("http://backend/locations/add", location_,
	 * Location.class); //sync with main backend db ↓↓↓↓↓↓
	 * restTemplate.postForObject("http://backend/accommodations/add",
	 * accommodation, AccommodationUnit.class);
	 * 
	 * return new ResponseEntity<AccommodationUnitAgent>(accommodationUnit,
	 * HttpStatus.CREATED); }
	 */

	// get Accommodation by ID
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<AccommodationUnitDTO> getAccommodationUnitByIdAgent(@PathVariable("id") Long id) {
		AccommodationUnitDTO temp = new AccommodationUnitDTO();
		temp.setAccommodationUnit(accommodationUnitService.readById(id));
		return new ResponseEntity<AccommodationUnitDTO>(temp, HttpStatus.OK);
	}

	// get all Accommodations
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<AccommodationUnitListDTO> getAllAccommodationUnitsAgent() {
		AccommodationUnitListDTO temp = new AccommodationUnitListDTO();
		temp.setAccommodationUnits(accommodationUnitService.readAll());
		return new ResponseEntity<AccommodationUnitListDTO>(temp, HttpStatus.OK);
	}

	// remove Accommodation
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ResponseEntity removeAccommodationUnitAgent(@RequestBody AccommodationUnitAgent accommodationUnit) {
		accommodationUnitService.delete(accommodationUnit);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// when confirming updateing main db as well
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/{id}/confirm", method = RequestMethod.GET)
	public ResponseEntity confirm(@RequestBody AccommodationUnitAgent accommodationUnit) {
		// accommodationUnit.setReserved(true);
		accommodationUnitService.confirmReservation(accommodationUnit);
		// database synchronisation ↓
		restTemplate.postForObject("http://backend/accommodations/add", accommodationUnit, AccommodationUnit.class);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
