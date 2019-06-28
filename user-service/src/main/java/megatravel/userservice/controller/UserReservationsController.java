package megatravel.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import megatravel.backend.service.ReservationService;
import megatravel.backend.dto.ReservationDTO;
import megatravel.backend.dto.ReservationListDTO;
import megatravel.userservice.service.UserReservationsService;

@RestController
@RequestMapping("/reservation") //handles a reservation presentation
public class UserReservationsController {

	@Autowired
	private ReservationService reservationsService;
	
	@Autowired
	private UserReservationsService userReservationsService;
	
	@RequestMapping("/{id}/all") // sve rezervacije od ID usera
	public ResponseEntity<ReservationListDTO> getAllReservations(@PathVariable(name = "id") Long id)
	{
		ReservationListDTO temp = new ReservationListDTO();
		temp.setReservations(userReservationsService.readAll(id));
		return new ResponseEntity<ReservationListDTO>(temp, HttpStatus.OK);
	}
	
	@RequestMapping("/{id}") // jedna rezervacija po ID
	public ResponseEntity<ReservationDTO> getReservationById(@PathVariable("id") Long id)
	{
		ReservationDTO temp = new ReservationDTO();
		temp.setReservation(userReservationsService.readById(id));
		return new ResponseEntity<ReservationDTO>(temp, HttpStatus.OK);
	}


	@SuppressWarnings("rawtypes") 
	@RequestMapping(value = "/{id}/cancel")
	public ResponseEntity cancelReservation(@RequestBody Long reservationId)
	{
		reservationsService.cancel(reservationId);
		return new ResponseEntity(HttpStatus.OK);
	}
}
