package megatravel.reservationservice.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	@RequestMapping(value = "/book", method = RequestMethod.POST)
	public ResponseEntity<Reservation> bookAccomodation(@RequestParam(name = "accID", required = false) String accID, 
			@RequestParam(name = "id", required = false) String userID, 
			@RequestParam(name = "checkincheckout", required = false) String datesIO) throws ParseException
	{
		System.out.println("\n"+ accID + userID + datesIO +"\n");
		String[] date = datesIO.split("x");
		Date checkin = new SimpleDateFormat("yyyy-MM-dd").parse(date[0]);
		Date checkout = new SimpleDateFormat("yyyy-MM-dd").parse(date[1]);
		
		if (userService.readById(Long.parseLong(userID)) != null) 
		  	{
				//registrated user premition 
				double numOfDays = getDateDiff(checkin, checkout, TimeUnit.DAYS); 
				Optional<AccommodationUnit> accommodationUnit = accommodationUnitService.readById(Long.parseLong(accID));
				double totalPrice = accommodationUnit.get().getPrice() * numOfDays; 
				
				Reservation reservation = new Reservation(accommodationUnit.get(), Long.parseLong(userID), checkin, checkout, totalPrice, null, "waiting-for-response", false);
				
				Optional<AccommodationUnit> accommodation = accommodationUnitService.readById(accommodationUnit.get().getId());
				
				List<String> dates = accommodation.get().getBookedDates();
				dates.add(checkin + "-" + checkout);
				
				accommodation.get().setBookedDates(dates); // datum-datum,datum-datum
				accommodationUnitService.update(accommodation.get(), accommodation.get().getId());
				
				reservationService.create(reservation);
				
				//update info on user.reservations and agent.reservations
				userService.updateData(Long.parseLong(userID), reservation);
				userService.updateData(accommodationUnit.get().getHostId(), reservation);
								
				return new ResponseEntity<Reservation>(reservation, HttpStatus.CREATED);
		  	}
			else
				return new ResponseEntity<Reservation>(HttpStatus.UNAUTHORIZED);		
	}

	static double getDateDiff(Date checkin, Date checkout, TimeUnit timeUnit) 
	{
	    long diffInMillies = checkout.getTime() - checkin.getTime();
	    
	    return (double)timeUnit.convert(diffInMillies,TimeUnit.DAYS);
	}
}


