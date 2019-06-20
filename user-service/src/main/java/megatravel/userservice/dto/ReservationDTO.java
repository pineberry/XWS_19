package megatravel.userservice.dto;

import java.util.Optional;

import lombok.Data;
import megatravel.userservice.model.Reservation;

@Data
public class ReservationDTO {
	
	private Optional<Reservation> reservation;

}
