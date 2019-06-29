package megatravel.backend.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import megatravel.backend.model.User;
import megatravel.backend.repository.UserRepository;

@Service
public class AuthorizationService {

	@Autowired
	private UserRepository userRepository;
	
	public boolean isAuthorized(String loginCredentials)
	{
		String[] credentials = loginCredentials.split("&"); //credentials[0] → username & credentials[1] → password
		
		List<User> users = userRepository.findAll();
		
		for (User user : users) {
			if(user.getUsername().equals(credentials[0]))
			{
				if(user.getPassword().equals(credentials[1]))
				{
					return true;
				}	
			}
		}
		return false;
	}
	
	public String expirationDate()
	{
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 10);
		date = calendar.getTime();
		DateFormat temp = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
		temp.setTimeZone(TimeZone.getTimeZone("GMT"));
		return temp.format(date);
	}
	
	public User getUserInfo(String loginCredentials)
	{
		String[] credentials = loginCredentials.split("&"); //credentials[0] → username & credentials[1] → password
		List<User> users = userRepository.findAll();
		User user = null;
		
		for (User u : users) {
			if(u.getUsername().equals(credentials[0]))
			{
				if(u.getPassword().equals(credentials[1]))
				{
					user = new User(u.getId(), u.getTypeOfUser(), u.getFirstName(), u.getLastName(), u.getUsername(),
							u.getPassword(), u.getAddress(), u.getPib(), u.getReservations());
					break;
				}	
			}
		}
		return user;
	}
	
}
