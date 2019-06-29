package megatravel.agentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import megatravel.backend.dto.ReservationListDTO;
import megatravel.backend.service.ReservationService;

@RestController
@RequestMapping("/reservation-agent")
public class ReservationsControllerAgent {


	
	@Autowired
	private ReservationService reservationService;
		
	@RequestMapping("/all")
	public ResponseEntity<ReservationListDTO> getAllAgentsReservations(@RequestBody Long hostID)
	{
		ReservationListDTO reservations = new ReservationListDTO();
		
		reservations.setReservations(reservationService.readAllFromHost(hostID));
		return new ResponseEntity<ReservationListDTO>(reservations, HttpStatus.OK);
	}
	
	@RequestMapping("/{id}/confirm")
	public ResponseEntity<ReservationListDTO> confirmReservation(@RequestBody Long reservationID)
	{
		reservationService.confirm(reservationID);
		
		ReservationListDTO reservations = new ReservationListDTO();
		reservations.setReservations(reservationService.readAllFromHost(reservationID));
		
		return new ResponseEntity<ReservationListDTO>(reservations, HttpStatus.OK);
	}
	
	@RequestMapping("/{id}/deny")
	public ResponseEntity<ReservationListDTO> denyReservation(@RequestBody Long reservationID)
	{
		reservationService.cancel(reservationID);
		
		ReservationListDTO reservations = new ReservationListDTO();
		reservations.setReservations(reservationService.readAllFromHost(reservationID));
		
		return new ResponseEntity<ReservationListDTO>(reservations, HttpStatus.OK);
	}
	
}
