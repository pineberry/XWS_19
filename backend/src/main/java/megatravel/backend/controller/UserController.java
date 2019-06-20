package megatravel.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import megatravel.backend.dto.UserDTO;
import megatravel.backend.dto.UserListDTO;
import megatravel.backend.model.User;
import megatravel.backend.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	//register new user
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ResponseEntity<User> registerUser(@RequestBody User user)
	{
		if (userService.create(user) == null) {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<User>(userService.create(user), HttpStatus.OK);
	}
	
	//read all
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<UserListDTO> getAllUsers()
	{
		UserListDTO temp = new UserListDTO();
		temp.setUsers(userService.readAll());
		return new ResponseEntity<UserListDTO>(temp, HttpStatus.OK);
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
