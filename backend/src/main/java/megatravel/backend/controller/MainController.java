package megatravel.backend.controller;

import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import megatravel.backend.dto.UserDTO;
import megatravel.backend.service.AuthorizationService;

@RestController
@RequestMapping("/")
public class MainController {
	
	@Autowired
	private AuthorizationService authorizationService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> login(@RequestHeader("authorization") String authorization)
	{
		String credentialsCoded = authorization;
		credentialsCoded = credentialsCoded.substring(6, authorization.length());		
		String credentials = new String(Base64.getDecoder().decode(credentialsCoded));

		if(authorizationService.isAuthorized(credentials))
		{
			HttpHeaders headers = new HttpHeaders();
			//("Set-Cookie", "test=somevalue; Domain=.mydomain.org; Expires=" + cookieLifeTime + "; Path=/; HTTPOnly")
			headers.add("Set-Cookie","Authorization=" + authorization + ";Domain=http://localhost:4200");
		
			UserDTO user = new UserDTO();
			user.setUser(authorizationService.getUserInfo(credentials));
			
			if(user.getUser().getTypeOfUser().equals("agent"))
			{
				//sync ISKLJUCIVO podatke za smestajne jedinice i to samo promene vezane za zauzece termina
				//nista drugo ne moze da se menja na drugim modulima pa logicno da nema ni promena	
			}
			
			return new ResponseEntity<UserDTO>(user, headers, HttpStatus.OK);
		}		
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
}
