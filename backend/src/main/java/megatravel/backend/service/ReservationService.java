package megatravel.backend.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
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
		/*List<AccommodationUnit> accommodations = accommodationUnitService.findAll();

		for (Reservation reservation : reservationRepository.findAll()) {
			for (AccommodationUnit accommodationUnit : accommodations) {
				if(accommodationUnit.getHostId() == hostId)
				{
					reservations.add(reservation);
				}
			}
		}*/

		return reservations;
	}

	public Reservation readById(Long id) {

		Optional<Reservation> r = reservationRepository.findById(id);

		Reservation reservation = new Reservation(r.get().getAccommodationUnitId(), r.get().getGuestId(), r.get().getCheckInDate(),
				r.get().getCheckOutDate(), r.get().getTotalPrice(), r.get().getStatus(), r.get().isConfirmed());

		return reservation;
	}

	public Reservation confirm(Long reservationID) {

		Optional<Reservation> r = reservationRepository.findById(reservationID);

		Reservation reservation = new Reservation(r.get().getId(), r.get().getAccommodationUnitId(), r.get().getGuestId(), r.get().getCheckInDate(),
				r.get().getCheckOutDate(), r.get().getTotalPrice(), "confirmed", true);

		return reservationRepository.save(reservation);
	}

	public Reservation cancel(Long reservationID) {

		Reservation reservation = reservationRepository.findById(reservationID).get();
		reservation.setId(reservationID);
		reservation.setStatus("canceled");
		reservation.setConfirmed(false);		
		
		AccommodationUnit accommodation = accommodationUnitService.findById(reservation.getAccommodationUnitId()).get();
		List<String> dates = accommodation.getBookedDates();
		List<String> datesToRemove = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
		
		for (String datePair : dates) {
			String date = sdf.format(reservation.getCheckInDate())+"-"+sdf.format(reservation.getCheckOutDate());
			if(datePair.equals(date)) {
				datesToRemove.add(datePair);
				break;
			}
		}
		dates.removeAll(datesToRemove);
		accommodation.setBookedDates(dates);
		
		accommodationUnitService.save(accommodation);

		return reservationRepository.save(reservation);
	}
}
