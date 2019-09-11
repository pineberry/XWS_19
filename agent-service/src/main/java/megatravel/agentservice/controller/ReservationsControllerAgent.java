package megatravel.agentservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/reservation-agent")
public class ReservationsControllerAgent {


/*
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
	}*/

}
