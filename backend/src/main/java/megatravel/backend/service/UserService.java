package megatravel.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import megatravel.backend.dto.UserDTO;
import megatravel.backend.model.Reservation;
import megatravel.backend.model.User;
import megatravel.backend.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;



	//create
	public User create(User user) 
	{
		
		if(!userAlreadyRegistered(user)){
			if (user.getTypeOfUser().equals("user") && user.getAddress() == null && user.getPib() == null){
				return userRepository.save(user); 
			} else if (user.getTypeOfUser().equals("agent") && (user.getAddress() != null && !user.getAddress().isEmpty()) && user.getPib() != null) {
				return userRepository.save(user); 
			} else if (user.getTypeOfUser().equals("admin")) {
				return userRepository.save(user);
			}
		} 
		else 
		{
			System.out.println("*******Exception, look up in UserService create(User user) method!*******");
			user = null;
		}
		return user;
	}

	//read by ID
	public User readById(Long id)
	{
		User user = userRepository.findById(id).get();
		return user;
	}

	//read all
	public List<UserDTO> readAll() 
	{
		List<UserDTO> users = new ArrayList<UserDTO>();
		for (User user : userRepository.findAll()) {
			UserDTO temp = new UserDTO();
			temp.setUser(user);
			users.add(temp);
		}
		return users;
	}

	//update
	//delete

	//help method that checks if an user already registered, returns false if it is not registered
	private boolean userAlreadyRegistered(User user) 
	{
		for (User temp : userRepository.findAll()) 
		{
			if(temp.getUsername().equals(user.getUsername()))
				return true;
		}
		return false;
	}

	//method for updating info on user.reservations and agent.reservations
	public void updateData(Long userID, Reservation reservation) {

		User user = readById(userID); //logika je da se cuva samo ID rezervacije u koloni rezervacija korisnika, ne ceo objekat rezervacije 
		List<Long> reservations = new ArrayList<Long>(); 
		reservations = user.getReservations(); 
		reservations.add(reservation.getId());
		user.setReservations(reservations); 
		userRepository.save(user);

	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public void delete(User user) {
		userRepository.delete(user);
	}

}
