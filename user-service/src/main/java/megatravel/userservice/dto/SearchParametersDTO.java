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
	
	public SearchParametersDTO(Location location, Date checkin, Date checkout, int numOfGuests) {
		super();
		this.location = location;
		this.checkin = checkin;
		this.checkout = checkout;
		this.numOfGuests = numOfGuests;
	}
	
}
