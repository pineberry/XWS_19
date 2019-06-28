package megatravel.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import megatravel.backend.model.Reservation;
import megatravel.backend.repository.ReservationRepository;

@Service
public class ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;
	
	public Reservation create(Reservation reservation)
	{
		return reservationRepository.save(reservation);
	}
	
	public Reservation update(Reservation reservation)
	{
		return reservationRepository.save(reservation);
	}
	
	public List<Reservation> readAll()
	{
		return reservationRepository.findAll();
	}
	
	public List<Reservation> readAllFromHost(Long id)
	{
		List<Reservation> reservations = new ArrayList<Reservation>();
		
		for (Reservation reservation : reservationRepository.findAll()) {
			if(reservation.getAccommodationUnit().getHostId() == id)
			{
				reservations.add(reservation);
			}
		}
		
		return reservations;
	}

	public Reservation readById(Long id) {
		
		Optional<Reservation> r = reservationRepository.findById(id);
		
		Reservation reservation = new Reservation(r.get().getAccommodationUnit(), r.get().getGuestId(), r.get().getCheckInDate(),
				r.get().getCheckOutDate(), r.get().getTotalPrice(), r.get().getChat(), r.get().getStatus(), r.get().isConfirmed());
	
		return reservation;
	}

	public Reservation confirm(Long id) {
		
		Optional<Reservation> r = reservationRepository.findById(id);
		
		Reservation reservation = new Reservation(r.get().getAccommodationUnit(), r.get().getGuestId(), r.get().getCheckInDate(),
				r.get().getCheckOutDate(), r.get().getTotalPrice(), r.get().getChat(), "confirmed", true);
	
		return reservationRepository.save(reservation);
	}
	
	public Reservation cancel(Long id) {
		
		Optional<Reservation> r = reservationRepository.findById(id);
		
		Reservation reservation = new Reservation(r.get().getAccommodationUnit(), r.get().getGuestId(), r.get().getCheckInDate(),
				r.get().getCheckOutDate(), r.get().getTotalPrice(), r.get().getChat(), "canceled", false);
	
		return reservationRepository.save(reservation);
	}
}
