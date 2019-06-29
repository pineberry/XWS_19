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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping("/test")
	public String string()
	{
		return "Hello";
	}

	// rezervisi smestaj za od do
	@RequestMapping(value = "/book/{id}/{checkincheckout}", method = RequestMethod.POST)
	public ResponseEntity<Reservation> bookAccomodation(@RequestBody AccommodationUnit accommodationUnit, 
			@PathVariable("id") Long userID, @PathVariable("checkincheckout") String datesIO) throws ParseException
	{
		String[] date = datesIO.split("-");
		Date checkin = new SimpleDateFormat("dd.MM.yyyy.").parse(date[0]);
		Date checkout = new SimpleDateFormat("dd.MM.yyyy.").parse(date[1]);
		System.out.println("test:" + accommodationUnit + date);
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
				
				reservationService.create(reservation);
				
				//update info on user.reservations and agent.reservations
				userService.updateData(userID, reservation);
				userService.updateData(accommodationUnit.getHostId(), reservation);
								
				return new ResponseEntity<Reservation>(reservation, HttpStatus.CREATED);
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


