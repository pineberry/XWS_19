package megatravel.backend.controller;

import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import megatravel.backend.dto.ReservationListDTO;
import megatravel.backend.dto.UserDTO;
import megatravel.backend.dto.UserListDTO;
import megatravel.backend.model.User;
import megatravel.backend.service.ReservationService;
import megatravel.backend.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	//register new user
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ResponseEntity<UserDTO> registerUser(@RequestBody User user)
	{
		System.out.println("\n################\n" + user + "\n################\n");
		
		HttpHeaders headers = new HttpHeaders();
		String auth = user.getUsername() + "&" + user.getPassword();
		//("Set-Cookie", "test=somevalue; Domain=.mydomain.org; Expires=" + cookieLifeTime + "; Path=/; HTTPOnly")
		headers.add("Set-Cookie","Authorization=" + "Basic " + Base64.getEncoder().encodeToString(auth.getBytes()) + ";Domain=http://localhost:4200");
		UserDTO u = new UserDTO();
		u.setUser(user);
		
		if (userService.create(user) == null) {
			return new ResponseEntity<UserDTO>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<UserDTO>(u, headers, HttpStatus.OK);
		}
	}
	
	//read all ---- localhost:8083/user/all
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<UserListDTO> getAllUsers()
	{
		UserListDTO temp = new UserListDTO();
		temp.setUsers(userService.readAll());
		return new ResponseEntity<UserListDTO>(temp, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/reservations/{id}", method = RequestMethod.GET)
	public ResponseEntity<ReservationListDTO> getAllUsersReservation(@PathVariable(name = "id") Long id) 
	{
		return new ResponseEntity<ReservationListDTO>(restTemplate.getForObject("http://user-service/reservation/" + id.toString() + "/all", ReservationListDTO.class), HttpStatus.OK);
	}
	
	//read by ID
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id)
	{
		UserDTO temp = new UserDTO();
		temp.setUser(userService.readById(id));
		return new ResponseEntity<UserDTO>(temp, HttpStatus.OK);
	}
}
