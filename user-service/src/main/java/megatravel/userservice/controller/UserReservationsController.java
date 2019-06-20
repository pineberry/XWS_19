package megatravel.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import megatravel.userservice.dto.ReservationDTO;
import megatravel.userservice.dto.ReservationListDTO;
import megatravel.userservice.service.UserReservationsService;

@RestController
@RequestMapping("/reservation")
public class UserReservationsController {

	@Autowired
	private UserReservationsService userReservationsService;
	
	@RequestMapping("/all")
	public ResponseEntity<ReservationListDTO> getAllReservations()
	{
		ReservationListDTO temp = new ReservationListDTO();
		temp.setReservations(userReservationsService.readAll());
		return new ResponseEntity<ReservationListDTO>(temp, HttpStatus.OK);
	}
	
	@RequestMapping("/{id}")
	public ResponseEntity<ReservationDTO> getReservationById(@PathVariable("id") Long id)
	{
		ReservationDTO temp = new ReservationDTO();
		temp.setReservation(userReservationsService.readById(id));
		return new ResponseEntity<ReservationDTO>(temp, HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/cancel/{id}")
	public ResponseEntity cancelReservation(@PathVariable("id") Long id)
	{
		userReservationsService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
