package megatravel.reservationservice.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import megatravel.backend.model.Reservation;

@RestController
@RequestMapping("/book-accommodation")
public class ReservationController {

	@Autowired
	private RestTemplate restTemplate;

	// rezervisi smestaj za od do
	@RequestMapping(value = "/book", method = RequestMethod.POST)
	public ResponseEntity<Reservation> bookAccomodation(@RequestParam(name = "accommodationId") String accommodationId, 
			@RequestParam(name = "userId") String userId, 
			@RequestParam(name = "checkin") String checkIn, 
			@RequestParam(name = "checkout") String checkOut) throws ParseException
	{

		return new ResponseEntity<Reservation>(restTemplate.postForObject("http://backend/reservation/book?userId=" + userId + "&accommodationId=" + accommodationId
				+ "&checkin=" + checkIn + "&checkout=" + checkOut, accommodationId, Reservation.class), HttpStatus.CREATED);
	}

	
}


