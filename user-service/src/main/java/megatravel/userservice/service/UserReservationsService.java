package megatravel.userservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import megatravel.backend.model.Reservation;
import megatravel.userservice.model.User;
import megatravel.userservice.repository.ReservationRepository;
import megatravel.userservice.repository.UserRepository;

/* this service is for retrieving information about existing users' reservations - reservation preview
 * also for cancellation of already booked accommodation - cancel reservation */
@Service
public class UserReservationsService {

	@Autowired
	private ReservationRepository reservationRepository;
	@Autowired
	private UserRepository userRepository;
	
	//read by id
	public Optional<Reservation> readById(Long id) 
	{
		return reservationRepository.findById(id);
	}
	
	//read all reservations from an user
	public List<Reservation> readAll(Long userId) 
	{
		Optional<User> user = userRepository.findById(userId);
		List<Reservation> reservations = new ArrayList<Reservation>();
		for (Reservation reservation : reservationRepository.findAll()) {
			if(reservation.getGuestId() == user.get().getId())
			{
				reservations.add(reservation);
			}	
		}
		return reservations;
	}
	
	//cancel reservation
	public void delete(Long id) 
	{
		reservationRepository.deleteById(id);
	}
}
