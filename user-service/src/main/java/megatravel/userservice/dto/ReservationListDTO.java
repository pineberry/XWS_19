package megatravel.userservice.dto;

import java.util.List;

import lombok.Data;
import megatravel.userservice.model.Reservation;

@Data
public class ReservationListDTO {

	private List<Reservation> reservations;
	
}
