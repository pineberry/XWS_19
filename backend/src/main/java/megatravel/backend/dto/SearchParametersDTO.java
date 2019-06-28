package megatravel.backend.dto;

import java.util.Date;
import java.util.Optional;

import lombok.Data;
import megatravel.backend.model.Location;

@Data
public class SearchParametersDTO {

	private Optional<Location> location;
	private Date checkin;
	private Date checkout;
	private int numOfGuests;
	
	public SearchParametersDTO(Optional<Location> location, Date checkin, Date checkout, int numOfGuests) {
		super();
		this.location = location;
		this.checkin = checkin;
		this.checkout = checkout;
		this.numOfGuests = numOfGuests;
	}
	
}
