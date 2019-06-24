package megatravel.reservationservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import megatravel.backend.model.Reservation;
import megatravel.reservationservice.repository.ReservationRepository;

@Service
public class ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;
	
	//create
	public Reservation create(Reservation reservation)
	{
		return reservationRepository.save(reservation);
	}
	
	//read ID & all - I have it on main back module
	
	//update - when user wants to book a place, status of reservation is "to be confirmed" so it needs some way to update the data
	public Reservation update(Reservation reservation)
	{
		return reservation;
	}
}
