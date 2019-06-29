package megatravel.agentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import megatravel.agentservice.model.UserAgent;
import megatravel.agentservice.service.UserServiceAgent;
import megatravel.backend.model.User;

@RestController
@RequestMapping("/user-agent")
public class UserControllerAgent {

	@Autowired
	private UserServiceAgent userService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<User> addUserAgent(@RequestBody User u)
	{
		UserAgent user = new UserAgent(u.getTypeOfUser(), u.getFirstName(), u.getLastName(), u.getUsername(), u.getPassword(), u.getAddress(), u.getPib());
		userService.create(user);
		return new ResponseEntity<User>(HttpStatus.OK);
	}
	
}
