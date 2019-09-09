package megatravel.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import megatravel.backend.model.AccommodationUnit;
import megatravel.backend.model.Reservation;
import megatravel.backend.repository.AccommodationUnitRepository;
import megatravel.backend.repository.ReservationRepository;

@Service
public class ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private AccommodationUnitRepository accommodationUnitService;

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

	public List<Reservation> readAllFromHost(Long hostId)
	{
		List<Reservation> reservations = new ArrayList<Reservation>();
		List<AccommodationUnit> accommodations = accommodationUnitService.findAll();

		for (Reservation reservation : reservationRepository.findAll()) {
			for (AccommodationUnit accommodationUnit : accommodations) {
				if(accommodationUnit.getHostId() == hostId)
				{
					reservations.add(reservation);
				}
			}
		}

		return reservations;
	}

	public Reservation readById(Long id) {

		Optional<Reservation> r = reservationRepository.findById(id);

		Reservation reservation = new Reservation(r.get().getAccommodationUnitId(), r.get().getGuestId(), r.get().getCheckInDate(),
				r.get().getCheckOutDate(), r.get().getTotalPrice(), r.get().getChat(), r.get().getStatus(), r.get().isConfirmed());

		return reservation;
	}

	public Reservation confirm(Long reservationID) {

		Optional<Reservation> r = reservationRepository.findById(reservationID);

		Reservation reservation = new Reservation(r.get().getId(), r.get().getAccommodationUnitId(), r.get().getGuestId(), r.get().getCheckInDate(),
				r.get().getCheckOutDate(), r.get().getTotalPrice(), r.get().getChat(), "confirmed", true);

		return reservationRepository.save(reservation);
	}

	public Reservation cancel(Long reservationID) {

		Optional<Reservation> r = reservationRepository.findById(reservationID);

		Reservation reservation = new Reservation(r.get().getId(), r.get().getAccommodationUnitId(), r.get().getGuestId(), r.get().getCheckInDate(),
				r.get().getCheckOutDate(), r.get().getTotalPrice(), r.get().getChat(), "canceled", false);

		return reservationRepository.save(reservation);
	}
}
