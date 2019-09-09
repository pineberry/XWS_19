package megatravel.userservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import megatravel.backend.repository.AccommodationUnitRepository;
import megatravel.backend.repository.ReservationRepository;
import megatravel.backend.repository.UserRepository;
import megatravel.backend.model.AccommodationUnit;
import megatravel.backend.model.Reservation;
import megatravel.backend.model.User;

/* this service is for retrieving information about existing users' reservations - reservation preview
 * also for cancellation of already booked accommodation - cancel reservation */
@Service
public class UserReservationsService {

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AccommodationUnitRepository accommodationRepository;

	//read by id
	public Optional<Reservation> readById(Long id) 
	{
		return reservationRepository.findById(id);
	}

	//read all reservations from an user
	public List<Reservation> readAll(Long userId) 
	{//za agenta ne radi zato sto ove proverava poklapaju li se ID od rezervacija-gost i usera koji je prosledjen i koji je esencijalno gost
		//a za agenta treba da se proveri sa ID od smestaj-host ako je agent 
		Optional<User> user = userRepository.findById(userId);
		List<Reservation> reservations = new ArrayList<Reservation>();
		if (user.get().getTypeOfUser().equals("agent"))
		{
			for (Reservation reservation : reservationRepository.findAll()) {
				Optional<AccommodationUnit> acc = accommodationRepository.findById(reservation.getAccommodationUnitId());
				if(acc.get().getHostId() == user.get().getId())
				{
					reservations.add(reservation);
				}	
			}
		} 
		else if (user.get().getTypeOfUser().equals("user"))
		{
			
			for (Reservation reservation : reservationRepository.findAll()) {
				if(reservation.getGuestId() == user.get().getId())
				{
					reservations.add(reservation);
				}	
			}
		}
		return reservations;
	}
}
