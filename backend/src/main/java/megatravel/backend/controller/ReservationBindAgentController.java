package megatravel.backend.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import megatravel.backend.SOAPConnector;
import megatravel.backend.dto.ReservationListDTO;
import megatravel.backend.model.AccommodationUnit;
import megatravel.backend.model.Reservation;
import megatravel.backend.repository.ReservationRepository;
import megatravel.backend.service.AccommodationUnitService;
import megatravel.backend.service.ReservationService;
import megatravel.backend.service.UserService;
import megatravel.backend.soap.UpdateAccommodationUnitRequest;
import megatravel.backend.soap.UpdateAccommodationUnitResponse;

@RestController
@RequestMapping("/reservation")
public class ReservationBindAgentController {

	@Autowired
	SOAPConnector soapConnector;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private AccommodationUnitService accommodationUnitService;
	
	
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
			
			Reservation reservation = new Reservation(accommodationUnit.get().getId(), Long.parseLong(userId), checkin, checkout, totalPrice, "waiting-for-response", false);
			
			Optional<AccommodationUnit> accommodation = accommodationUnitService.readById(accommodationUnit.get().getId());
			
			List<String> dates = accommodation.get().getBookedDates(); //pokupili stare
			dates.add(checkin + "-" + checkout); //dodali novi par
			accommodation.get().setBookedDates(dates); // setovali nove datume, datum-datum,datum-datum
			accommodationUnitService.update(accommodation.get(), accommodation.get().getId());
			
			reservationService.create(reservation);
			
			//update info on user.reservations and agent.reservations
			userService.updateData(Long.parseLong(userId), reservation);
			userService.updateData(accommodationUnit.get().getHostId(), reservation);
			
			UpdateAccommodationUnitRequest req = new UpdateAccommodationUnitRequest();
			req.setAccommodationUnit(accommodationUnitService.readById(accommodationUnit.get().getId()).get());
			
			soapConnector.callWebService("http://localhost:8081/ws/schemas", req);
							
			return new ResponseEntity<Reservation>(reservation, HttpStatus.CREATED);
	  	}
		else
			return new ResponseEntity<Reservation>(HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping(value = "/{id}/confirm", method = RequestMethod.POST)
	public ResponseEntity<ReservationListDTO> confirmReservation(@RequestBody Long reservationID, @PathVariable(name = "id") Long id)
	{
		reservationService.confirm(reservationID);

		ReservationListDTO reservations = new ReservationListDTO();
		reservations.setReservations(reservationService.readAllFromHost(reservationID));
		
		return new ResponseEntity<ReservationListDTO>(reservations, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}/deny", method = RequestMethod.POST)
	public ResponseEntity<ReservationListDTO> denyReservation(@RequestBody Long reservationID, @PathVariable(name = "id") Long id)
	{
		reservationService.cancel(reservationID);
		
		Reservation reservation = reservationRepository.findById(reservationID).get();
				
		AccommodationUnit accommodation = accommodationUnitService.readById(reservation.getAccommodationUnitId()).get();
		List<String> dates = accommodation.getBookedDates();
		List<String> datesToRemove = new ArrayList<String>();
		
		for (String datePair : dates) {
			if(datePair.equals(reservation.getCheckInDate()+"-"+reservation.getCheckOutDate())) {
				datesToRemove.add(datePair);
				break;
			}
		}
		dates.removeAll(datesToRemove);
		accommodation.setBookedDates(dates);
		
		UpdateAccommodationUnitRequest request = new UpdateAccommodationUnitRequest();
		request.setAccommodationUnit(accommodation);
		
		soapConnector.callWebService("http://localhost:8081/ws/schemas", request);
		
		ReservationListDTO reservations = new ReservationListDTO();
		reservations.setReservations(reservationService.readAllFromHost(reservationID));
		
		return new ResponseEntity<ReservationListDTO>(reservations, HttpStatus.OK);
	}
	
	
	static double getDateDiff(Date checkin, Date checkout) {
	    long diffInMillies = checkout.getTime() - checkin.getTime();
	    
	    return (double)TimeUnit.DAYS.convert(diffInMillies,TimeUnit.MILLISECONDS);
	}
}
