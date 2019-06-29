package megatravel.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import megatravel.backend.dto.ReservationListDTO;
import megatravel.backend.dto.UserDTO;
import megatravel.backend.dto.UserListDTO;
import megatravel.backend.model.User;
import megatravel.backend.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	//register new user
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ResponseEntity<User> registerUser(@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("typeOfUser") String typeOfUser,
			@RequestParam("address") String address, @RequestParam("pib") String pib)
	{
		User user;
		System.out.println(">>>>>>>>>>" + typeOfUser);
		if (typeOfUser.equals("user")) {
			user = new User(typeOfUser, firstName, lastName, username, password, null, null, null);
			System.out.println(">>>>>>>>>>" + user);
		} else {
			Long pib_ = Long.parseLong(pib);
			user = new User(typeOfUser, firstName, lastName, username, password, address, pib_, null);
		}
		
		if (userService.create(user) == null) {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		else 
		{
			restTemplate.postForObject("http://agent-service/user-agent/add", user, User.class);
			return new ResponseEntity<User>(user, HttpStatus.OK);
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
	
	@RequestMapping(value = "/{id}/reservations", method = RequestMethod.GET)
	public ResponseEntity<ReservationListDTO> getAllUsersReservation(@PathVariable(name = "id") Long id) 
	{
		System.out.println(">>>>>>>>>>>>>>>>>" + id);
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
