package megatravel.backend.controller;

import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import megatravel.backend.dto.AccommodationUnitListDTO;
import megatravel.backend.dto.UserDTO;
import megatravel.backend.service.AuthorizationService;

@RestController
@RequestMapping("/")
public class MainController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private AuthorizationService authorizationService;
	
	@RequestMapping(value = "/sdasd", method = RequestMethod.GET)
	public ResponseEntity<AccommodationUnitListDTO> search(@RequestParam(name = "location") String location, @RequestParam(name = "checkin") String checkin,
			@RequestParam(name = "checkout") String checkout, @RequestParam(name = "guests") String numOfGuests) 
	{
		System.out.println("\nevo me!\n");
		AccommodationUnitListDTO accommodations = restTemplate.getForObject("http://user-service/search?location=" 
				+ location + "&checkin=" + checkin + "&checkout=" + checkout + "&guests=" +  numOfGuests, AccommodationUnitListDTO.class);
		return new ResponseEntity<AccommodationUnitListDTO>(accommodations, HttpStatus.OK);
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
