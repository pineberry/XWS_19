package megatravel.userservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import megatravel.userservice.model.Reservation;
import megatravel.userservice.repository.ReservationRepository;

/* this service is for retrieving information about existing users' reservations - reservation preview
 * also for cancellation of already booked accommodation - cancel reservation */
@Service
public class UserReservationsService {

	@Autowired
	private ReservationRepository reservationRepository;
	
	//read by id
	public Optional<Reservation> readById(Long id) 
	{
		return reservationRepository.findById(id);
	}
	
	//read all
	public List<Reservation> readAll() 
	{
		return reservationRepository.findAll();
	}
	
	//cancel reservation
	public void delete(Long id) 
	{
		reservationRepository.deleteById(id);
	}
}
