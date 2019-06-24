package megatravel.reservationservice.controller;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import megatravel.backend.model.AccommodationUnit;
import megatravel.backend.model.Reservation;
import megatravel.backend.model.User;
import megatravel.reservationservice.service.ReservationService;

@RestController
@RequestMapping("/accommodation-reservation")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;

	// /accommodation-reservation/book → create(Reservation reservation)
	@RequestMapping(value = "/book/{checkin}-{checkout}")
	public ResponseEntity<Reservation> bookAccomodation(@RequestBody AccommodationUnit accommodationUnit, 
			@RequestBody User user, @PathVariable("checkin") Date checkin, @PathVariable("checkout") Date checkout)
	{
		double numOfDays = getDateDiff(checkin,checkout,TimeUnit.MINUTES);
		double totalPrice = accommodationUnit.getPrice() * numOfDays;
		Reservation reservation = new Reservation(accommodationUnit, user, checkin, checkout, totalPrice, null, "waiting-for-response", false);
		return new ResponseEntity<Reservation>(reservationService.create(reservation), HttpStatus.CREATED);
	}
	
	// /accommodation-reservation/cancel

	public static double getDateDiff(Date checkin, Date checkout, TimeUnit timeUnit) {
	    long diffInMillies = checkout.getTime() - checkin.getTime();
	    return (double)timeUnit.convert(diffInMillies,TimeUnit.DAYS);
	}
}


