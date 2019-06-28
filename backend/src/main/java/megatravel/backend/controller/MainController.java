package megatravel.backend.controller;

import java.util.Base64;
import java.util.Date;
import java.util.Optional;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import megatravel.backend.dto.AccommodationUnitListDTO;
import megatravel.backend.dto.SearchParametersDTO;
import megatravel.backend.dto.UserDTO;
import megatravel.backend.model.Location;
import megatravel.backend.model.User;
import megatravel.backend.service.AuthorizationService;
import megatravel.backend.service.LocationService;

@RestController
@RequestMapping("/")
public class MainController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private LocationService locationService;
	
	@Autowired
	private AuthorizationService authorizationService;
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ResponseEntity<AccommodationUnitListDTO> search(@PathVariable(name = "location") Long id, @PathVariable(name = "checkin") Date checkin,
			@PathVariable(name = "checkout") Date checkout, @PathVariable(name = "guests") int numOfGuests) 
	{
		Optional<Location> location = locationService.readById(id);
		SearchParametersDTO parameter = new SearchParametersDTO(location, checkin, checkout, numOfGuests);
		ResponseEntity<AccommodationUnitListDTO> accommodations = restTemplate.exchange("http://user-service/search/", HttpMethod.GET, new HttpEntity<SearchParametersDTO>(parameter), AccommodationUnitListDTO.class);
		return new ResponseEntity<AccommodationUnitListDTO>(accommodations.getBody(), HttpStatus.OK);
	}
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> login(@RequestHeader("authorization") String authorization, @CookieValue(value = "test", required = false) String test)
	{
		String credentialsCoded = authorization;
		credentialsCoded = credentialsCoded.substring(6, authorization.length());		
		String credentials = new String(Base64.getDecoder().decode(credentialsCoded));
		System.out.println(">>>>>>>>>>>>>"+credentials);
		if(authorizationService.isAuthorized(credentials))
		{
			System.out.println(">>>>>>>>>>>>>"+credentials);
			HttpHeaders cookie = new HttpHeaders();
			cookie.add("Authorization", "true");
			UserDTO user = new UserDTO();
			user.setUser(authorizationService.getUserInfo(credentials));
			return new ResponseEntity<UserDTO>(user, cookie, HttpStatus.CREATED);
		}		
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
}
