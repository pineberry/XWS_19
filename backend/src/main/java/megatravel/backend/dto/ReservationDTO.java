package megatravel.backend.dto;

import java.util.Optional;

import lombok.Data;
import megatravel.backend.model.Reservation;

@Data
public class ReservationDTO {
	
	private Optional<Reservation> reservation;

}
