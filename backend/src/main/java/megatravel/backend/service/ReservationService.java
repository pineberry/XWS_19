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

		/*
		 * ##########


Reservation(id=1, accommodationUnitId=2, guestId=2, checkInDate=2020-08-09 00:00:00.0, checkOutDate=2020-10-09 00:00:00.0, totalPrice=915.0, chat=null, 
status=waiting-for-response, confirmed=false)


##########
##########


Reservation(id=1, accommodationUnitId=2, guestId=2, checkInDate=2020-08-09 00:00:00.0, checkOutDate=2020-10-09 00:00:00.0, totalPrice=915.0, chat=null, 
status=waiting-for-response, confirmed=false)


##########
		 * */


		System.out.println("##########\n\n\n"+reservationID+"\n\n\n##########");
		Optional<Reservation> r = reservationRepository.findById(reservationID);

		System.out.println("##########\n\n\n"+r.get()+"\n\n\n##########");
		Reservation reservation = new Reservation(r.get().getId(), r.get().getAccommodationUnitId(), r.get().getGuestId(), r.get().getCheckInDate(),
				r.get().getCheckOutDate(), r.get().getTotalPrice(), r.get().getChat(), "confirmed", true);
		System.out.println("##########\n\n\n"+reservation+"\n\n\n##########");
		return reservationRepository.save(reservation);
	}

	public Reservation cancel(Long id) {

		Optional<Reservation> r = reservationRepository.findById((long)id);

		Reservation reservation = new Reservation(r.get().getAccommodationUnitId(), r.get().getGuestId(), r.get().getCheckInDate(),
				r.get().getCheckOutDate(), r.get().getTotalPrice(), r.get().getChat(), "canceled", false);

		//update on user
		//		userService.updateData(r.get().getGuestId(), reservation);
		//update on agent
		//		userService.updateData(accommodationUnitService.findById((long) reservation.getAccommodationUnitId()).get().getHostId(), reservation);

		return reservationRepository.save(reservation);
	}
}
