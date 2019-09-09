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
	public ResponseEntity<Reservation> bookAccomodation(@RequestParam(name = "accommodationId") String accommodationId, 
			@RequestParam(name = "userId") String userId, 
			@RequestParam(name = "checkin") String checkIn, 
			@RequestParam(name = "checkout") String checkOut) throws ParseException
	{

		Date checkin = new SimpleDateFormat("dd.MM.yyyy.").parse(checkIn);
		Date checkout = new SimpleDateFormat("dd.MM.yyyy.").parse(checkOut);
		
		if (userService.readById(Long.parseLong(userId)) != null) //dodatna autorizacija i bulletproofing xD
		  	{
				//registrated user premition 
				double numOfDays = getDateDiff(checkin, checkout); 
				System.out.println("\n\n>>>>" + numOfDays + "\n\n");
				Optional<AccommodationUnit> accommodationUnit = accommodationUnitService.readById(Long.parseLong(accommodationId));
				double totalPrice = accommodationUnit.get().getDefaultPrice() * numOfDays; 
				
				Reservation reservation = new Reservation(accommodationUnit.get().getId(), Long.parseLong(userId), checkin, checkout, totalPrice, null, "waiting-for-response", false);
				
				Optional<AccommodationUnit> accommodation = accommodationUnitService.readById(accommodationUnit.get().getId());
				
				List<String> dates = accommodation.get().getBookedDates(); //pokupili stare
				dates.add(checkin + "-" + checkout); //dodali novi par
				accommodation.get().setBookedDates(dates); // setovali nove datume, datum-datum,datum-datum
				accommodationUnitService.update(accommodation.get(), accommodation.get().getId());
				
				reservationService.create(reservation);
				
				//update info on user.reservations and agent.reservations
				userService.updateData(Long.parseLong(userId), reservation);
				userService.updateData(accommodationUnit.get().getHostId(), reservation);
								
				return new ResponseEntity<Reservation>(reservation, HttpStatus.CREATED);
		  	}
			else
				return new ResponseEntity<Reservation>(HttpStatus.UNAUTHORIZED);		
	}

	static double getDateDiff(Date checkin, Date checkout) {
	    long diffInMillies = checkout.getTime() - checkin.getTime();
	    
	    return (double)TimeUnit.DAYS.convert(diffInMillies,TimeUnit.MILLISECONDS);
	}
}


