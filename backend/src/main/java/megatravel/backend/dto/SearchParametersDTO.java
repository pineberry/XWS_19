package megatravel.backend.dto;

import java.util.Date;
import lombok.Data;

@Data
public class SearchParametersDTO {

	private String city;
	private Date checkin;
	private Date checkout;
	private int numOfGuests;
	
	public SearchParametersDTO(String city, Date checkin, Date checkout, int numOfGuests) {
		super();
		this.city = city;
		this.checkin = checkin;
		this.checkout = checkout;
		this.numOfGuests = numOfGuests;
	}
	
}
