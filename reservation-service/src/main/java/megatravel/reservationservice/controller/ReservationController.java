package megatravel.reservationservice.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;
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
import megatravel.backend.service.AccommodationUnitService;
import megatravel.backend.service.ReservationService;
import megatravel.backend.service.UserService;

@RestController
@RequestMapping("/book-accommodation")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private AccommodationUnitService accommodationUnitService;
	
	@Autowired
	private UserService userService;

	// rezervisi smestaj za od do
	@RequestMapping(value = "/book/{checkin}-{checkout}")
	public ResponseEntity<Reservation> bookAccomodation(@RequestBody AccommodationUnit accommodationUnit, 
			@RequestBody Long userID, @PathVariable("checkin") Date checkin, @PathVariable("checkout") Date checkout)
	{
		if (userService.readById(userID) != null) 
		  	{
				//registrated user premition 
				double numOfDays = getDateDiff(checkin, checkout, TimeUnit.DAYS); 
				double totalPrice = accommodationUnit.getPrice() * numOfDays; 
				
				Reservation reservation = new Reservation(accommodationUnit, userID, checkin, checkout, totalPrice, null, "waiting-for-response", false);
				
				Optional<AccommodationUnit> accommodation = accommodationUnitService.readById(accommodationUnit.getId());
				
				List<String> dates = accommodation.get().getBookedDates();
				dates.add(checkin + "-" + checkout);
				
				accommodation.get().setBookedDates(dates); // datum-datum,datum-datum
				accommodationUnitService.update(accommodation.get(), accommodation.get().getId());
				
				return new ResponseEntity<Reservation>(reservationService.create(reservation), HttpStatus.CREATED);
		  	}
			else
				return new ResponseEntity<Reservation>(HttpStatus.INTERNAL_SERVER_ERROR);		
	}

	static double getDateDiff(Date checkin, Date checkout, TimeUnit timeUnit) 
	{
	    long diffInMillies = checkout.getTime() - checkin.getTime();
	    
	    return (double)timeUnit.convert(diffInMillies,TimeUnit.DAYS);
	}
}


