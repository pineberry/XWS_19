package megatravel.userservice.dto;

import java.util.Date;

import lombok.Data;
import megatravel.backend.model.Location;

@Data
public class SearchParametersDTO {

	private Location location;
	private Date checkin;
	private Date checkout;
	private int numOfGuests;
	
}
