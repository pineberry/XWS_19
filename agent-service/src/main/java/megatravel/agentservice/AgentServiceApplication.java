package megatravel.agentservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import megatravel.agentservice.model.AccommodationUnitAgent;
import megatravel.agentservice.model.AmenityAgent;
import megatravel.agentservice.model.ImageAgent;
import megatravel.agentservice.model.LocationAgent;
import megatravel.agentservice.model.ReviewAgent;
import megatravel.agentservice.service.AccommodationUnitServiceAgent;
import megatravel.agentservice.service.AmenityServiceAgent;
import megatravel.agentservice.service.ImageServiceAgent;
import megatravel.agentservice.service.LocationServiceAgent;
import megatravel.backend.dto.AccommodationUnitListDTO;
import megatravel.backend.dto.AmenityListDTO;
import megatravel.backend.dto.ImageListDTO;
import megatravel.backend.dto.LocationListDTO;

@SpringBootApplication
@EnableEurekaClient
@EnableConfigurationProperties
@EntityScan(basePackages = {"megatravel.agentservice.model", "megatravel.backend.model"})
@ComponentScan(basePackages = {"megatravel.agentservice.controller", "megatravel.agentservice.service", "megatravel.backend.service", "megatravel.backend"})
@EnableJpaRepositories({"megatravel.backend.repository", "megatravel.agentservice.repository"})
public class AgentServiceApplication {

		
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private LocationServiceAgent locationService;
	
	@Autowired
	private AmenityServiceAgent amenityService;
	
	@Autowired
	private ImageServiceAgent imageService;
	
	/*
	 * @Autowired private ReviewServiceAgent reviewService;
	 */
	
	@Autowired
	private AccommodationUnitServiceAgent accommodationService;
	
	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {

		
		LocationListDTO locations = restTemplate.getForObject("http://backend/location/all", LocationListDTO.class);
		
		for (megatravel.backend.model.Location l: locations.getLocations()) 
		{
			LocationAgent location = new LocationAgent(l.getState(), l.getCity(), l.getAddress(), l.getLatitude(), l.getLongitude());
			locationService.create(location);
		}
		
		
		AmenityListDTO amenities = restTemplate.getForObject("http://backend/amenity/all", AmenityListDTO.class);
		
		for (megatravel.backend.model.Amenity a : amenities.getAmenities()) 
		{
			AmenityAgent amenity = new AmenityAgent(a.getAmenity());
			amenityService.create(amenity);
		}
		
		
		ImageListDTO images = restTemplate.getForObject("http://backend/image/all", ImageListDTO.class);
		
		for (megatravel.backend.model.Image i : images.getImages())
		{
			ImageAgent image = new ImageAgent(i.getSrc());
			imageService.create(image);
			
		}
		
		
		/*
		 * ReviewListDTO reviews =
		 * restTemplate.getForObject("http://backend/review/all", ReviewListDTO.class);
		 * 
		 * for (megatravel.backend.model.Review r : reviews.getReviews()) { ReviewAgent
		 * review = new ReviewAgent(r.getReviewContent(), r.getMark());
		 * reviewService.create(review); }
		 */
		
		
		AccommodationUnitListDTO accommodations = restTemplate.getForObject("http://backend/accommodation/all", AccommodationUnitListDTO.class);
		
		for (megatravel.backend.model.AccommodationUnit a : accommodations.getAccommodationUnits()) {
			
			LocationAgent location_ = new LocationAgent(a.getLocation().getState(), a.getLocation().getCity(), a.getLocation().getAddress(), a.getLocation().getLatitude(), a.getLocation().getLongitude());
			
			List<ImageAgent> images_ = new ArrayList<ImageAgent>();
			
			for (megatravel.backend.model.Image i : a.getImages()) {
				images_.add(new ImageAgent(i.getSrc()));
			}
			
			List<AmenityAgent> amenities_ = new ArrayList<AmenityAgent>();
			
			for (megatravel.backend.model.Amenity a_ : a.getAmenities()) {
				amenities_.add(new AmenityAgent(a_.getAmenity()));
			}
			
			List<ReviewAgent> reviews_ = new ArrayList<ReviewAgent>();
			
			for (megatravel.backend.model.Review r : a.getReviews()) {
				reviews_.add(new ReviewAgent(r.getReviewContent(), r.getMark()));
			}
			
			AccommodationUnitAgent accommodation = new AccommodationUnitAgent(a.getId(), location_, a.getType(), a.getCategory(), a.getDescription(), a.getUnitCapacity(), images_, amenities_, a.getCancelationPeriod(), a.getPrice(), a.getBookedDates());
			
			accommodationService.create(accommodation);
		}

	}
	
	public static void main(String[] args) {
		SpringApplication.run(AgentServiceApplication.class, args);
	}

}
