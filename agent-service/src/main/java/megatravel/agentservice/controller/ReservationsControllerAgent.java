package megatravel.agentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import megatravel.backend.dto.ReservationListDTO;
import megatravel.backend.service.ReservationService;

@RestController
@RequestMapping("/reservation-agent")
public class ReservationsControllerAgent {



	@Autowired
	private ReservationService reservationService;

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/all")
	public ResponseEntity<ReservationListDTO> getAllAgentsReservations(@RequestBody Long hostID)
	{
		ReservationListDTO reservations = new ReservationListDTO();

		reservations.setReservations(reservationService.readAllFromHost(hostID));
		return new ResponseEntity<ReservationListDTO>(reservations, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/confirm", method = RequestMethod.POST)
	public ResponseEntity<ReservationListDTO> confirmReservation(@RequestBody Long reservationID, @PathVariable(name = "id") Long id)
	{
		return new ResponseEntity<ReservationListDTO>(restTemplate.postForObject("http://backend/user/reservation/" + id + "/confirm", reservationID, ReservationListDTO.class), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/deny", method = RequestMethod.POST)
	public ResponseEntity<ReservationListDTO> denyReservation(@RequestBody Long reservationID, @PathVariable(name = "id") Long id)
	{
		return new ResponseEntity<ReservationListDTO>(restTemplate.postForObject("http://backend/user/reservation/" + id + "/deny", reservationID, ReservationListDTO.class), HttpStatus.OK);
	}

}
